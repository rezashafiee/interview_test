package shafiee.mr.interviewtest.repository

import androidx.lifecycle.LiveData
import shafiee.mr.interviewtest.AppExecutors
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.db.PlaceDetailsDao
import shafiee.mr.interviewtest.model.PlaceDetailsResponse
import shafiee.mr.interviewtest.network.ApiResponse
import shafiee.mr.interviewtest.network.NetworkBoundResource
import shafiee.mr.interviewtest.network.PlaceDetailsApi
import shafiee.mr.interviewtest.network.Resource
import javax.inject.Inject

class PlaceDetailsRepository @Inject constructor(
    private val placeDetailsDao: PlaceDetailsDao,
    private val placeDetailsApi: PlaceDetailsApi,
    private val appExecutors: AppExecutors
) {

    fun loadPlaceDetails(id: Int): LiveData<Resource<PlaceDetailsResponse>> {
        return object :
            NetworkBoundResource<PlaceDetailsResponse, PlaceDetailsResponse>(appExecutors) {

            override fun saveCallResult(item: PlaceDetailsResponse) {
                placeDetailsDao.insert(item)
            }

            override fun shouldFetch(data: PlaceDetailsResponse?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<PlaceDetailsResponse> {
                return placeDetailsDao.getById(id)
            }

            override fun createCall(): LiveData<ApiResponse<PlaceDetailsResponse>> {
                return placeDetailsApi.getPlaceDetails(
                    id.toString(),
                    Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    Constants.V
                )
            }

        }.asLiveData
    }

}