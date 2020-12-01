package shafiee.mr.interviewtest.ui.places.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import shafiee.mr.interviewtest.base.BaseFragment
import shafiee.mr.interviewtest.databinding.FragmentPlaceDetailsBinding
import shafiee.mr.interviewtest.model.PlaceDetailsResponse
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.utils.shortToast
import shafiee.mr.interviewtest.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class PlaceDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentPlaceDetailsBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory


    companion object {
        fun newInstance() = PlaceDetailsFragment()
    }

    private lateinit var viewModel: PlaceDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(PlaceDetailsViewModel::class.java)

        val args: PlaceDetailsFragmentArgs by navArgs()
        observePlaceDetails(args.placeId!!)
    }

    private fun observePlaceDetails(id: String) {
        viewModel.placeDetailsRepository.loadPlaceDetails(id).removeObservers(viewLifecycleOwner)
        viewModel.placeDetailsRepository.loadPlaceDetails(id).observe(viewLifecycleOwner, {
            it.let {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        setUiValues(it.data)
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        it.message?.let { message -> requireContext().shortToast(message) }
                    }
                }
            }
        })
    }

    private fun setUiValues(placeDetailsResponse: PlaceDetailsResponse?) {
        binding.textViewPlaceName.text = placeDetailsResponse?.data?.venue?.name
        binding.textViewPlaceDetail1.text = placeDetailsResponse?.data?.venue?.description
        binding.textViewPlaceDetail2.text = placeDetailsResponse?.data?.venue?.rating
    }

}