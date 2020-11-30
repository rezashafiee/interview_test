package shafiee.mr.interviewtest.ui.places.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import shafiee.mr.interviewtest.model.Response
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlacesListRepository
import javax.inject.Inject

class PlacesListViewModel @Inject constructor(
    var placesListRepository: PlacesListRepository
) : ViewModel() {

    private var tempList: MutableList<Response>? = null
    private var resourceLive: LiveData<Resource<List<Response>>>

    init {
        tempList = mutableListOf()
        resourceLive = MutableLiveData()
    }

    fun loadPlacesList(
        currentLocation: PersistenceLocation?,
        page: Int
    ): LiveData<Resource<List<Response>>> {
        val tempResource = placesListRepository.loadPlacesList(currentLocation, page).value
        tempList?.add(tempResource?.data!!)
        val resource: Resource<List<Response>> =
            Resource(tempResource?.status!!, tempList, tempResource.message)
        resourceLive.value?.data
        return resourceLive
    }
}