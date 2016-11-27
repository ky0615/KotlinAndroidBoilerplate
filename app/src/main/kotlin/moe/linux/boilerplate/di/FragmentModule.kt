package moe.linux.boilerplate.di

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(val fragment: Fragment) {

    @Provides
    fun provideContext(): Context = fragment.context

    @Provides
    fun provideFragmentManager(): FragmentManager = fragment.fragmentManager
}