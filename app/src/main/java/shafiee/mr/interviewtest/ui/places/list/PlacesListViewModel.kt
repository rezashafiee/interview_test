package shafiee.mr.interviewtest.ui.places.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import shafiee.mr.interviewtest.model.Response
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlacesListRepository
import javax.inject.Inject

class PlacesListViewModel @Inject constructor
    (var placesListRepository: PlacesListRepository) : ViewModel() {

    private var placesList: MediatorLiveData<Resource<Response>>? = null

    fun loadPlaceList(): LiveData<Resource<Response>> {
        if (placesList == null) {
            placesList = MediatorLiveData()
            //placesList?.value = Resource.Loading(null)
        }

        val source = placesListRepository.loadPlacesList()

        placesList?.addSource(source) {
            placesList?.value = it
            placesList?.removeSource(source)
        }

        return placesList as LiveData<Resource<Response>>
    }
}