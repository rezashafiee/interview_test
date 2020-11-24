package shafiee.mr.interviewtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import shafiee.mr.interviewtest.ui.places.PlacesActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePlacesActivity(): PlacesActivity
}