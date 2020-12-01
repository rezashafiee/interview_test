package shafiee.mr.interviewtest.di.places.details

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import shafiee.mr.interviewtest.network.PlaceDetailsApi

@Module
object PlaceDetailsModule {

    @Provides
    fun providesPlaceDetailsApi(retrofit: Retrofit): PlaceDetailsApi {
        return retrofit.create(PlaceDetailsApi::class.java)
    }
}