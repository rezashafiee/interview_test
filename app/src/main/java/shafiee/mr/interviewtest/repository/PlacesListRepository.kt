package shafiee.mr.interviewtest.repository

import androidx.lifecycle.LiveData
import shafiee.mr.interviewtest.AppExecutors
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.db.PlacesListDao
import shafiee.mr.interviewtest.model.Response
import shafiee.mr.interviewtest.network.ApiResponse
import shafiee.mr.interviewtest.network.NetworkBoundResource
import shafiee.mr.interviewtest.network.PlaceListApi
import shafiee.mr.interviewtest.network.Resource
import javax.inject.Inject

class PlacesListRepository @Inject constructor(
    private val placesListApi: PlaceListApi,
    private val placesListDao: PlacesListDao,
    private val appExecutors: AppExecutors
) {

    fun loadPlacesList(): LiveData<Resource<Response>> {

        return object : NetworkBoundResource<Response, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                placesListDao.insert(item)
            }

            override fun shouldFetch(data: Response?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Response> {
                return placesListDao.getAll()
            }

            override fun createCall(): LiveData<ApiResponse<Response>> {

                return placesListApi.explorePlaces(
                    Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    Constants.V,
                    "35.715959, 51.478906"
                )/*.switchMap {
                        if (it is ApiErrorResponse)
                            return@switchMap ApiResponse.create(Response(-1, ""))
                    }*/
            }


            /* override fun saveCallResult(item: List<Venue>) {
                 placesListDao.insert(item)
             }

             override fun shouldFetch(data: List<Venue>?): Boolean {
                 return data.isNullOrEmpty()
             }

             override fun loadFromDb(): LiveData<List<Venue>> {
                 return placesListDao.getAll()
             }

             override fun createCall(): LiveData<ApiResponse<List<Venue>>> {

                 val venuesLiveData: MutableLiveData<ApiResponse<List<Venue>>> = MutableLiveData()

                 val test = placesListApi.explorePlaces(
                     Constants.CLIENT_ID,
                     Constants.CLIENT_SECRET,
                     Constants.V,
                     "35.715959, 51.478906"
                 )*//*.switchMap { apiResponse ->
                    Log.d("Imchini", "Imchini")
                    println(apiResponse)
                    val venues: MutableList<Venue?> = mutableListOf()
                    if (apiResponse is ApiSuccessResponse) {
                        apiResponse.body.placesListData?.groups?.forEach { group ->
                            group.items?.forEach {
                                venues.add(it.venue)
                            }
                        }
                    }
                    venuesLiveData.value = ApiSuccessResponse(venues.mapNotNull { it })
                    venuesLiveData
                }*//*
                return venuesLiveData
            }*/


        }.asLiveData
    }
}