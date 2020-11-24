package shafiee.mr.interviewtest.di.places.details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shafiee.mr.interviewtest.di.ViewModelKey
import shafiee.mr.interviewtest.ui.places.details.PlaceDetailsViewModel

@Module
abstract class PlaceDetailsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlaceDetailsViewModel::class)
    abstract fun bindPlaceDetailsViewModel(placeDetailsViewModel: PlaceDetailsViewModel): ViewModel
}