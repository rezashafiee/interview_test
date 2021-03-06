package shafiee.mr.interviewtest.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import shafiee.mr.interviewtest.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build();
    }
}