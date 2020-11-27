package shafiee.mr.interviewtest.di.places.list

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import shafiee.mr.interviewtest.network.PlaceListApi

@Module
object PlacesListModule {

    @Provides
    fun providePlacesListApi(retrofit: Retrofit): PlaceListApi {
        return retrofit.create(PlaceListApi::class.java)
    }
}