package shafiee.mr.interviewtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import shafiee.mr.interviewtest.di.places.PlacesFragmentBuildersModule
import shafiee.mr.interviewtest.ui.places.PlacesActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [PlacesFragmentBuildersModule::class]
    )
    abstract fun contributePlacesActivity(): PlacesActivity
}