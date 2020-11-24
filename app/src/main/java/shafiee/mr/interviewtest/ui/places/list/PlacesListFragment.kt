package shafiee.mr.interviewtest.ui.places.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import shafiee.mr.interviewtest.R

class PlacesListFragment : Fragment() {

    companion object {
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
        viewModel = ViewModelProvider(this).get(PlacesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}