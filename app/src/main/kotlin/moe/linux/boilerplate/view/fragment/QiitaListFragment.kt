package moe.linux.boilerplate.view.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.linux.boilerplate.adapter.QiitaListAdapter
import moe.linux.boilerplate.api.qiita.QiitaApiClient
import moe.linux.boilerplate.databinding.FragmentQiitaListBinding
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

    lateinit private var binding: FragmentQiitaListBinding
    private val qiitaListAdapter: QiitaListAdapter = QiitaListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.injectTo(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQiitaListBinding.inflate(inflater, container, false)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = qiitaListAdapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData() {
        qiitaClient.stockList("dll7")
            .subscribe({
                qiitaListAdapter.apply {
                    list.clear()
                    list.addAll(it)
                    notifyDataSetChanged()
                }
            }, {
                Snackbar.make(binding.coordinatorLayout, "エラーが発生しました。", Snackbar.LENGTH_LONG).show()
            })
    }
}