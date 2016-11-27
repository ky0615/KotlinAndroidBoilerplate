package moe.linux.boilerplate.fragment

import android.support.v4.app.Fragment
import moe.linux.boilerplate.activity.BaseActivity
import moe.linux.boilerplate.di.ActivityComponent

abstract class BaseFragment : Fragment() {

    abstract fun injectDependencies(component: ActivityComponent)

    fun getBaseActivity(): BaseActivity = activity as BaseActivity
}
