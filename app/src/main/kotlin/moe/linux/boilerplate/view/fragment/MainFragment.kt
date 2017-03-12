package moe.linux.boilerplate.view.fragment

import android.os.Bundle

class MainFragment() : BaseFragment() {
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
