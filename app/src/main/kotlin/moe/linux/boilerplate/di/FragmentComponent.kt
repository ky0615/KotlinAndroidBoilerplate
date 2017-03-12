package moe.linux.boilerplate.di

import dagger.Subcomponent
import moe.linux.boilerplate.di.scope.FragmentScope
import moe.linux.boilerplate.view.fragment.FrontFragment
import moe.linux.boilerplate.view.fragment.GithubListFragment
import moe.linux.boilerplate.view.fragment.MainFragment
import moe.linux.boilerplate.view.fragment.QiitaListFragment

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
