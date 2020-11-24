package shafiee.mr.interviewtest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import shafiee.mr.interviewtest.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}