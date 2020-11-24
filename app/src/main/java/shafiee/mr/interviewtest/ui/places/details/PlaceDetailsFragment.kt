package shafiee.mr.interviewtest.ui.places.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import shafiee.mr.interviewtest.R
import shafiee.mr.interviewtest.base.BaseFragment

class PlaceDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = PlaceDetailsFragment()
    }

    private lateinit var viewModel: PlaceDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlaceDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}