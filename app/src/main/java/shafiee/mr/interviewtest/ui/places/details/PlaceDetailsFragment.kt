package shafiee.mr.interviewtest.ui.places.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import shafiee.mr.interviewtest.base.BaseFragment
import shafiee.mr.interviewtest.databinding.FragmentPlaceDetailsBinding

class PlaceDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentPlaceDetailsBinding

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
        viewModel = ViewModelProvider(this).get(PlaceDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}