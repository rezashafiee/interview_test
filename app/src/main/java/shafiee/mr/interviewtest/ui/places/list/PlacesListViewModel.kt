package shafiee.mr.interviewtest.ui.places.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.model.Item
import shafiee.mr.interviewtest.model.Response
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlacesListRepository
import javax.inject.Inject

class PlacesListViewModel @Inject constructor(
    var placesListRepository: PlacesListRepository
) : ViewModel() {

    var pagedList: LiveData<PagedList<Item>>

    init {
        val placeItemDataSourceFactory = PlaceItemDataSourceFactory(placesListRepository)
        val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.PAGE_SIZE.toInt()).build()
        pagedList = LivePagedListBuilder(placeItemDataSourceFactory, pagedListConfig).build()
    }

    fun loadPlacesList(currentLocation: PersistenceLocation?): LiveData<Resource<Response>> {
        return placesListRepository.loadPlacesList(currentLocation, 1)
    }
}