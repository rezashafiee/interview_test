package shafiee.mr.interviewtest.repository

import android.location.Location
import androidx.lifecycle.LiveData
import shafiee.mr.interviewtest.AppExecutors
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.db.PlacesListDao
import shafiee.mr.interviewtest.model.PlaceListResponse
import shafiee.mr.interviewtest.model.persistence_models.PersistenceLocation
import shafiee.mr.interviewtest.network.ApiResponse
import shafiee.mr.interviewtest.network.NetworkBoundResource
import shafiee.mr.interviewtest.network.PlaceListApi
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.utils.PreferencesManager
import javax.inject.Inject

class PlacesListRepository @Inject constructor(
    private val placesListApi: PlaceListApi,
    private val placesListDao: PlacesListDao,
    private val preferencesManager: PreferencesManager,
    private val appExecutors: AppExecutors
) {

    fun loadPlacesList(
        currentLocation: PersistenceLocation?,
        page: Int
    ): LiveData<Resource<PlaceListResponse>> {

        return object : NetworkBoundResource<PlaceListResponse, PlaceListResponse>(appExecutors) {
            override fun saveCallResult(item: PlaceListResponse) {
                placesListDao.insert(item)
            }

            override fun shouldFetch(data: PlaceListResponse?): Boolean {

                val lastLocationLatitude = preferencesManager?.getLastLocationLat()
                val lastLocationLongitude = preferencesManager?.getLastLocationLng()

                when {
                    lastLocationLatitude == null && lastLocationLongitude == null -> {
                        return if (currentLocation != null) {
                            saveCurrentLocation(currentLocation)
                            true
                        } else
                            false
                    }
                    currentLocation == null -> {
                        return checkPage(page)
                    }
                    else -> {
                        return if (isCurrentLocationNearToLastLocation(
                                currentLocation,
                                lastLocationLatitude, lastLocationLongitude
                            )
                        ) checkPage(page)
                        else {
                            saveCurrentLocation(currentLocation)
                            clearData()
                            true
                        }
                    }
                }
            }

            override fun loadFromDb(): LiveData<PlaceListResponse> {
                //return placesListDao.getAll()
                return placesListDao.getByPage(page)
            }

            override fun createCall(): LiveData<ApiResponse<PlaceListResponse>> {

                return placesListApi.explorePlaces(
                    Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    Constants.V,
                    "${currentLocation?.lat}, ${currentLocation?.lng}",
                    Constants.PAGE_SIZE,
                    page.toString()
                )
            }
        }.asLiveData
    }

    private fun saveCurrentLocation(location: PersistenceLocation) {
        // Save current location to preferences
        preferencesManager?.saveLocationLat(location.lat.toString())
        preferencesManager?.saveLocationLng(location.lng.toString())
    }

    private fun checkPage(currentPage: Int): Boolean {
        return if (currentPage > preferencesManager?.getLastPage()!!) {
            preferencesManager.savePageNumber(currentPage)
            true
        } else
            false
    }

    private fun clearData() {
        // clear last page
        preferencesManager?.removeLastPage()

        // clear previous responses from DB
        appExecutors.diskIO().execute {
            placesListDao.removeAll()
        }
    }

    private fun isCurrentLocationNearToLastLocation(
        currentLocation: PersistenceLocation?,
        lastLocationLat: Double?, lastLocationLng: Double?
    ): Boolean {
        val distances = FloatArray(1)
        try {
            Location.distanceBetween(
                currentLocation?.lat!!,
                currentLocation.lng!!,
                lastLocationLat!!,
                lastLocationLng!!,
                distances
            )

        } catch (e: Exception) {
            print(e.toString())
        }

        return distances[0] <= 100.0 // 100 meters
    }
}