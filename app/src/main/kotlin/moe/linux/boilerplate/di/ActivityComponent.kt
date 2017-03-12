package moe.linux.boilerplate.di

import dagger.Subcomponent
import moe.linux.boilerplate.view.activity.MainActivity
import moe.linux.boilerplate.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ActivityModule::class
))
interface ActivityComponent {
    fun injectTo(activity: MainActivity)

    fun plus(module: FragmentModule): FragmentComponent
}
