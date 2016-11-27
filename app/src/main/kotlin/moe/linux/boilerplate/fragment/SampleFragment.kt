package moe.linux.boilerplate.fragment

import moe.linux.boilerplate.di.ActivityComponent
import moe.linux.boilerplate.di.FragmentModule

class SampleFragment : BaseFragment() {
    override fun injectDependencies(component: ActivityComponent) {
        component.plus(FragmentModule(this))
                .injectTo(this)
    }

}