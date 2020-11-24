package shafiee.mr.interviewtest.di.places

import dagger.Module
import dagger.android.ContributesAndroidInjector
import shafiee.mr.interviewtest.di.places.list.PlacesListModule
import shafiee.mr.interviewtest.di.places.list.PlacesListViewModelsModule
import shafiee.mr.interviewtest.ui.places.list.PlacesListFragment

@Module
abstract class PlacesFragmentBuildersModule {

    @ContributesAndroidInjector(
        modules = [PlacesListModule::class, PlacesListViewModelsModule::class]
    )
    abstract fun contributePlacesListFragment(): PlacesListFragment
}