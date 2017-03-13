package moe.linux.boilerplate.di

import dagger.Component
import moe.linux.boilerplate.MainApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    ApiModule::class
))
interface AppComponent {
    fun injectTo(app: MainApplication)

    fun plus(module: ActivityModule): ActivityComponent
}
