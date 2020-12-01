package shafiee.mr.interviewtest.ui.places.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import shafiee.mr.interviewtest.model.PlaceListResponse
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlacesListRepository
import javax.inject.Inject

class PlacesListViewModel @Inject constructor(
    var placesListRepository: PlacesListRepository
) : ViewModel() {

    fun loadPlacesList(
        currentLocation: PersistenceLocation?,
        page: Int
    ): LiveData<Resource<PlaceListResponse>> {
        return placesListRepository.loadPlacesList(currentLocation, page)
    }
}