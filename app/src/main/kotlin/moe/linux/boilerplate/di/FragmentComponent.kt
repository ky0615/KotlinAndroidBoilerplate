package moe.linux.boilerplate.di

import dagger.Subcomponent
import moe.linux.boilerplate.di.scope.FragmentScope
import moe.linux.boilerplate.fragment.SampleFragment

@FragmentScope
@Subcomponent(modules = arrayOf(
        FragmentModule::class
))
interface FragmentComponent {
    fun injectTo(fragment: SampleFragment)
}