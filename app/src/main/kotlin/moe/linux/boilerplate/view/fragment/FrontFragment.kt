package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import moe.linux.boilerplate.view.activity.MainActivity

class FrontFragment : BaseFragment() {
    override val TAG: String = FrontFragment.TAG

    override fun getPage(): MainActivity.Page = MainActivity.Page.FRONT

    companion object {
        val TAG: String = FrontFragment::class.simpleName ?: "UndefinedClass"

        fun newInstance(): FrontFragment {
            return FrontFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.injectTo(this)
    }
}
