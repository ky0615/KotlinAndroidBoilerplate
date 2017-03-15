package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import moe.linux.boilerplate.di.FragmentComponent
import moe.linux.boilerplate.di.FragmentModule
import moe.linux.boilerplate.view.activity.BaseActivity
import moe.linux.boilerplate.view.activity.MainActivity

abstract class BaseFragment : Fragment() {
    abstract val TAG: String

    val fragmentComponent: FragmentComponent by lazy {
        getBaseActivity().activityComponent.plus(FragmentModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getBaseActivity(): BaseActivity = activity as BaseActivity

    fun getMainActivity(): MainActivity = activity as MainActivity

    abstract fun getPage(): MainActivity.Page
}
