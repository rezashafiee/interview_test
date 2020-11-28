package shafiee.mr.interviewtest.repository

import android.location.Location
import androidx.lifecycle.LiveData
import shafiee.mr.interviewtest.AppExecutors
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.db.PlacesListDao
import shafiee.mr.interviewtest.model.Response
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
    private val preferencesManager: PreferencesManager?,
    private val appExecutors: AppExecutors
) {

    fun loadPlacesList(currentLocation: PersistenceLocation?): LiveData<Resource<Response>> {

        return object : NetworkBoundResource<Response, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                placesListDao.insert(item)
            }

            override fun shouldFetch(data: Response?): Boolean {

                val lastLocationLatitude = preferencesManager?.getLastLocationLat()
                val lastLocationLongitude = preferencesManager?.getLastLocationLng()

                when {
                    lastLocationLatitude == null && lastLocationLongitude == null -> {
                        return if (currentLocation != null) {
                            // save location
                            preferencesManager?.saveCurrentLocationLat(currentLocation.lat.toString())
                            preferencesManager?.saveCurrentLocationLng(currentLocation.lng.toString())
                            true
                        } else
                            false
                    }
                    currentLocation == null -> return false
                    else -> {
                        return if (isCurrentLocationNearToLastLocation(
                                currentLocation,
                                lastLocationLatitude, lastLocationLongitude
                            )
                        )
                            false
                        else {
                            preferencesManager?.saveCurrentLocationLat(currentLocation.lat.toString())
                            preferencesManager?.saveCurrentLocationLng(currentLocation.lng.toString())
                            true
                        }
                    }
                }
            }

            override fun loadFromDb(): LiveData<Response> {
                return placesListDao.getAll()
            }

            override fun createCall(): LiveData<ApiResponse<Response>> {

                return placesListApi.explorePlaces(
                    Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    Constants.V,
                    "${currentLocation?.lat}, ${currentLocation?.lng}"
                )
            }
        }.asLiveData
    }

    fun isCurrentLocationNearToLastLocation(
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