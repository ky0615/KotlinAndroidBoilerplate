package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.linux.boilerplate.api.qiita.QiitaApiClient
import moe.linux.boilerplate.databinding.FragmentQiitaListBinding
import moe.linux.boilerplate.viewModel.QiitaListAdapter
import moe.linux.boilerplate.viewModel.QiitaListViewModel
import javax.inject.Inject

class QiitaListFragment : BaseFragment() {
    override val TAG: String = QiitaListFragment::class.simpleName ?: "UndefinedClass"

    companion object {
        fun newInstance(): QiitaListFragment {
            return QiitaListFragment()
        }
    }

    @Inject
    lateinit var qiitaClient: QiitaApiClient

    @Inject
    lateinit var qiitaListViewModel: QiitaListViewModel

    lateinit private var binding: FragmentQiitaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.injectTo(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQiitaListBinding.inflate(inflater, container, false).apply {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = QiitaListAdapter(context, qiitaListViewModel.list)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        qiitaListViewModel.start {
            it.printStackTrace()
            Snackbar.make(binding.coordinatorLayout, "cause error: ${it.message}", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        qiitaListViewModel.destroy()
    }
}