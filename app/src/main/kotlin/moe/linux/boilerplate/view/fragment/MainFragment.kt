package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import moe.linux.boilerplate.view.activity.MainActivity

class MainFragment() : BaseFragment() {
    override fun getPage(): MainActivity.Page = MainActivity.Page.FRONT

    override val TAG: String = MainFragment::class.simpleName ?: "UndefinedClass"

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.injectTo(this)
    }


}
