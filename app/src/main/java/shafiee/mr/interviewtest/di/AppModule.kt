package shafiee.mr.interviewtest.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shafiee.mr.interviewtest.Constants
import shafiee.mr.interviewtest.db.PlaceDetailsDao
import shafiee.mr.interviewtest.db.PlacesDatabase
import shafiee.mr.interviewtest.db.PlacesListDao
import shafiee.mr.interviewtest.utils.LiveDataCallAdapterFactory
import shafiee.mr.interviewtest.utils.PreferencesManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideGSONInstance(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    fun providePlacesDatabase(application: Application): PlacesDatabase {
        return Room.databaseBuilder(application, PlacesDatabase::class.java, "Places.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePlacesListDao(db: PlacesDatabase): PlacesListDao {
        return db.placesListDao()
    }

    @Singleton
    @Provides
    fun providePlaceDetailsDao(db: PlacesDatabase): PlaceDetailsDao {
        return db.placeDetailsDao()
    }

    @Provides
    fun providePreferencesManager(application: Application): PreferencesManager {
        return PreferencesManager.getInstance(application)!!
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(null)
            .error(null)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }
}