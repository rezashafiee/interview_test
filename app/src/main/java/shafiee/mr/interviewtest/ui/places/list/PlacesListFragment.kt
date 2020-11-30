package shafiee.mr.interviewtest.ui.places.list

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.gms.common.api.ResolvableApiException
import shafiee.mr.interviewtest.base.BaseFragment
import shafiee.mr.interviewtest.databinding.FragmentPlacesListBinding
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.utils.*
import shafiee.mr.interviewtest.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class PlacesListFragment : BaseFragment(), LocationSupportView {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    lateinit var requestManager: RequestManager

    private var locationUtils: LocationUtils? = null
    private lateinit var binding: FragmentPlacesListBinding
    private var placesListAdapter: PlacesListAdapter? = null
    private var currentLocation: PersistenceLocation? = null

    companion object {
        private const val REQUEST_CHECK_LOCATION_SETTINGS = 1001
        private const val REQUEST_LOCATION_PERMISSION = 1002
        const val TAG = "PlacesListFragment"

        fun newInstance() = PlacesListFragment()
    }

    private lateinit var viewModel: PlacesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(PlacesListViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesManager.removeLastPage()
        currentLocation = PersistenceLocation(null, null)
        locationUtils = LocationUtils(requireContext(), this)

        val layoutManager = LinearLayoutManager(binding.recyclerViewPlacesList.context)
        binding.recyclerViewPlacesList.layoutManager = layoutManager
        placesListAdapter = PlacesListAdapter(requestManager)
        binding.recyclerViewPlacesList.adapter = placesListAdapter

        binding.recyclerViewPlacesList.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                currentLocation?.lat = preferencesManager.getLastLocationLat()
                currentLocation?.lng = preferencesManager.getLastLocationLng()
                observePlacesList(currentLocation, current_page)
            }
        })

        binding.buttonExplore.setOnClickListener {
            locationUtils?.createLocationRequest()
        }
    }

    private fun observePlacesList(location: PersistenceLocation?, pageNumber: Int) {
        viewModel.loadPlacesList(location, pageNumber).removeObservers(viewLifecycleOwner)
        viewModel.loadPlacesList(location, pageNumber).observe(viewLifecycleOwner, {
            it.let {
                when (it.status) {
                    Resource.Status.LOADING -> {
                    }
                    Resource.Status.SUCCESS -> {
                        // just set the list
                        println("Imchini  fetched location = $location and data = ${it.data?.placesListData?.groups}")
                        val items = it.data?.placesListData?.groups?.get(0)?.items
                        if (pageNumber > 1)
                            placesListAdapter?.updateList(items)
                        else
                            placesListAdapter?.setList(items)
                    }
                    Resource.Status.ERROR -> {
                    }
                }
            }
        })
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                locationUtils?.getLocation()
            } else {
                requireContext().shortToast("Location permission denied !")
                observePlacesList(null, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CHECK_LOCATION_SETTINGS) {
            if (resultCode == Activity.RESULT_OK)
                locationUtils?.getLocation()
            else
                observePlacesList(null, 1)
        }

    }

    override fun showLocationSettingDialog(exception: ResolvableApiException) {

        // Show the dialog by calling startResolutionForResult() in Activity ,
        // and startIntentSenderForResult() in fragment
        // and check the result in onActivityResult().

        /*exception.startResolutionForResult(
            requireActivity(),
            REQUEST_CHECK_LOCATION_SETTINGS
        )*/

        startIntentSenderForResult(
            exception.resolution.intentSender,
            REQUEST_CHECK_LOCATION_SETTINGS, null, 0, 0, 0, null
        )
    }

    override fun showPermissionRequestDialog() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), REQUEST_LOCATION_PERMISSION
        )
    }

    override fun onLocationProvided(location: Location?) {
        Log.d(
            TAG,
            "onLocationProvided: lat = ${location?.latitude} , lng = ${location?.longitude}"
        )

        // convert received Location to database transferable Location
        val currentLocation =
            PersistenceLocation(lat = location?.latitude, lng = location?.longitude)

        observePlacesList(currentLocation, 1)
    }
}