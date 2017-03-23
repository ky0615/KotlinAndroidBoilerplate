package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import moe.linux.boilerplate.databinding.FragmentFrontBinding
import moe.linux.boilerplate.view.activity.MainActivity
import moe.linux.boilerplate.viewModel.BoardViewModel
import javax.inject.Inject

class FrontFragment : BaseFragment() {
    override val TAG: String = FrontFragment.TAG

    override fun getPage(): MainActivity.Page = MainActivity.Page.FRONT

    @Inject
    lateinit var db: DatabaseReference

    @Inject
    lateinit var viewModel: BoardViewModel

    lateinit var binding: FragmentFrontBinding

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFrontBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.binding = binding
        viewModel.start()
    }
}
