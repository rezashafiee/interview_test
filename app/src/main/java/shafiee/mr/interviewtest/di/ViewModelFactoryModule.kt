package shafiee.mr.interviewtest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import shafiee.mr.interviewtest.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {
    // This class is created to generate dependencies for factory class


    // The result of this method is same as @Provide methods
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}