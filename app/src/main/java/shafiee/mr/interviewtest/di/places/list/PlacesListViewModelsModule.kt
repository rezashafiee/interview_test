package shafiee.mr.interviewtest.di.places.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shafiee.mr.interviewtest.di.ViewModelKey
import shafiee.mr.interviewtest.ui.places.list.PlacesListViewModel

@Module
abstract class PlacesListViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlacesListViewModel::class)
    abstract fun bindPlacesListViewModel(placesListViewModel: PlacesListViewModel): ViewModel
}