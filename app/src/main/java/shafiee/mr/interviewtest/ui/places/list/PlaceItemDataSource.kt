package shafiee.mr.interviewtest.ui.places.list

import androidx.paging.PageKeyedDataSource
import shafiee.mr.interviewtest.model.Item
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.repository.PlacesListRepository

class PlaceItemDataSource(
    private val placesListRepository: PlacesListRepository,
    private val currentLocation: PersistenceLocation?
) :
    PageKeyedDataSource<Int, Item>() {

    companion object {
        private const val FIRST_PAGE = 1;
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        placesListRepository.loadPlacesList(currentLocation, FIRST_PAGE)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        TODO("Not yet implemented")
    }
}