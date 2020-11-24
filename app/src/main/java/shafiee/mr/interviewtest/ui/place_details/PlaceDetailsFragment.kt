package shafiee.mr.interviewtest.ui.place_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import shafiee.mr.interviewtest.R

class PlaceDetailsFragment : Fragment() {

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