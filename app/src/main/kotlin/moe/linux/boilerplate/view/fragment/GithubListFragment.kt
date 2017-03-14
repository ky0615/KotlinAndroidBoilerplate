package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.linux.boilerplate.api.github.GithubApiClient
import moe.linux.boilerplate.databinding.GithubListFragmentBinding
import javax.inject.Inject

class GithubListFragment : BaseFragment() {
    override val TAG: String = GithubListFragment.TAG

    companion object {
        val TAG = GithubListFragment::class.simpleName ?: "UndefinedClass"
        fun newInstance(): GithubListFragment {
            return GithubListFragment()
        }
    }

    @Inject
    lateinit var client: GithubApiClient

    lateinit private var binding: GithubListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.injectTo(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = GithubListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
