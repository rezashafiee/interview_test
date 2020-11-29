package shafiee.mr.interviewtest.ui.places.list

import androidx.paging.DataSource
import shafiee.mr.interviewtest.model.Item
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.repository.PlacesListRepository

class PlaceItemDataSourceFactory(
    private val placesListRepository: PlacesListRepository,
    private val currentLocation: PersistenceLocation?
) :
    DataSource.Factory<Int, Item>() {

    override fun create(): DataSource<Int, Item> {
        return PlaceItemDataSource(placesListRepository)
    }
}