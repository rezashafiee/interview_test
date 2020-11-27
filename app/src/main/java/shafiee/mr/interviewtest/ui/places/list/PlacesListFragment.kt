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
import com.google.android.gms.common.api.ResolvableApiException
import shafiee.mr.interviewtest.R
import shafiee.mr.interviewtest.base.BaseFragment
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.utils.LocationSupportView
import shafiee.mr.interviewtest.utils.LocationUtils
import shafiee.mr.interviewtest.utils.shortToast
import shafiee.mr.interviewtest.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class PlacesListFragment : BaseFragment(), LocationSupportView {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private var locationUtils: LocationUtils? = null

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
    ): View? {
        return inflater.inflate(R.layout.fragment_places_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(PlacesListViewModel::class.java)

        subscribeObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //locationUtils = LocationUtils(requireContext(), this)

        //locationUtils?.createLocationRequest()


    }

    private fun subscribeObservers() {
        viewModel.placesListRepository.loadPlacesList().removeObservers(viewLifecycleOwner)
        viewModel.placesListRepository.loadPlacesList().observe(viewLifecycleOwner, {
            it.let {
                when (it.status) {
                    Resource.Status.LOADING -> {
                    }
                    Resource.Status.SUCCESS -> {
                        println("Imchini tedad = ${it.data?.placesListData?.totalResults}")
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
            } else
                requireContext().shortToast("Location permission denied !")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CHECK_LOCATION_SETTINGS)
                locationUtils?.getLocation()
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

    override fun onLocationProvided(lastLocation: Location?) {
        Log.d(
            TAG,
            "onLocationProvided: lat = ${lastLocation?.latitude} , lng = ${lastLocation?.longitude}"
        )
    }
}