package shafiee.mr.interviewtest.ui.places.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import shafiee.mr.interviewtest.model.Response
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlacesListRepository
import javax.inject.Inject

class PlacesListViewModel @Inject constructor
    (var placesListRepository: PlacesListRepository) : ViewModel() {

    fun loadPlacesList(): LiveData<Resource<Response>> {
        return placesListRepository.loadPlacesList()
    }
}