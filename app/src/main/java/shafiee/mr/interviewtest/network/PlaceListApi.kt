package shafiee.mr.interviewtest.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query
import shafiee.mr.interviewtest.model.Response

interface PlaceListApi {

    @GET("/v2/venues/explore")
    fun explorePlaces(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("v") v: String,
        @Query("ll") ll: String,
        @Query("limit") pageSize: String,
        @Query("offset") pageNumber: String
    ): LiveData<ApiResponse<Response>>

    @GET("")
    fun getPlaceDetails(
        @Query("VENUE_ID") placeId: String
    ): LiveData<ApiResponse<Response>>
}