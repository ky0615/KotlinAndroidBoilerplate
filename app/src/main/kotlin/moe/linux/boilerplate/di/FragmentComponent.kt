package moe.linux.boilerplate.di

import dagger.Subcomponent
import moe.linux.boilerplate.di.scope.FragmentScope
import moe.linux.boilerplate.fragment.FrontFragment
import moe.linux.boilerplate.fragment.GithubListFragment
import moe.linux.boilerplate.fragment.MainFragment
import moe.linux.boilerplate.fragment.QiitaListFragment

@FragmentScope
@Subcomponent(modules = arrayOf(
    FragmentModule::class
))
interface FragmentComponent {
    fun injectTo(fragment: FrontFragment)
    fun injectTo(fragment: MainFragment)
    fun injectTo(fragment: QiitaListFragment)
    fun injectTo(fragment: GithubListFragment)
}
