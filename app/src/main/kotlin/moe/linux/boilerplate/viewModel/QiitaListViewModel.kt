package moe.linux.boilerplate.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import moe.linux.boilerplate.R
import moe.linux.boilerplate.api.qiita.QiitaApiClient
import moe.linux.boilerplate.api.qiita.StockListResponse
import moe.linux.boilerplate.databinding.ViewQiitaListContentBinding
import moe.linux.boilerplate.util.view.DataBindingViewHolder
import moe.linux.boilerplate.util.view.Navigator
import moe.linux.boilerplate.util.view.ObservableListRecyclerAdapter
import moe.linux.boilerplate.util.view.ViewModel
import timber.log.Timber
import javax.inject.Inject

class QiitaListViewModel @Inject constructor(
    val compositeDisposable: CompositeDisposable,
    val navigator: Navigator,
    val qiitaApiClient: QiitaApiClient) : BaseObservable(), ViewModel {

    val list = ObservableArrayList<QiitaStockViewModel>()

    fun start(error: (Throwable) -> Unit = {}) {
        compositeDisposable.add(qiitaApiClient.stockList("dll7")
            .subscribe(
                { list.addAll(it.map { it.convertToQiitaStockViewModel() }).apply { it.forEach { Timber.d("add: ${it.title}") } } },
                error))
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    fun StockListResponse.convertToQiitaStockViewModel(): QiitaStockViewModel {
        return QiitaStockViewModel(navigator, this)
    }

}

class QiitaStockViewModel(val navigator: Navigator, val stock: StockListResponse) : BaseObservable(), ViewModel {
    override fun destroy() {
    }

    fun onClick(view: View) {
        Timber.d("click with: ${stock.title}")
        navigator.navigateToWebPage(stock.url)
    }
}

class QiitaListAdapter(context: Context, list: ObservableList<QiitaStockViewModel>)
    : ObservableListRecyclerAdapter<QiitaStockViewModel, DataBindingViewHolder<ViewQiitaListContentBinding>>(context, list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<ViewQiitaListContentBinding>
        = DataBindingViewHolder(context, R.layout.view_qiita_list_content, parent)

    override fun onBindViewHolder(holder: DataBindingViewHolder<ViewQiitaListContentBinding>, position: Int) {
        holder.binding.viewModel = getItem(position)
        holder.binding.executePendingBindings()
    }
}
