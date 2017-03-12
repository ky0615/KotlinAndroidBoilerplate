package moe.linux.boilerplate.view.fragment

import android.os.Bundle

class FrontFragment : BaseFragment() {
    override val TAG: String = FrontFragment.TAG

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