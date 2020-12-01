package shafiee.mr.interviewtest.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query
import shafiee.mr.interviewtest.model.PlaceDetailsResponse

interface PlaceDetailsApi {

    @GET("/v2/venues/")
    fun getPlaceDetails(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("v") v: String,
        @Query("VENUE_ID") placeId: String
    ): LiveData<ApiResponse<PlaceDetailsResponse>>
}