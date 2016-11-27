package moe.linux.boilerplate

import android.app.Application
import dagger.Lazy
import moe.linux.boilerplate.di.AppComponent
import moe.linux.boilerplate.di.AppModule
import moe.linux.boilerplate.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class MainApplication : Application() {

    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependency()

        if (BuildConfig.DEBUG)
            Timber.plant(debugTree.get())
    }

    private fun initDependency() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.injectTo(this)
    }
}
