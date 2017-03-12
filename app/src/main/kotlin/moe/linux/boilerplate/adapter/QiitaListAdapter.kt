package moe.linux.boilerplate.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import moe.linux.boilerplate.api.qiita.StockListResponse
import moe.linux.boilerplate.databinding.ViewQiitaListContentBinding

class QiitaListAdapter : RecyclerView.Adapter<QiitaListViewHolder>() {
    val list: MutableList<StockListResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaListViewHolder =
        QiitaListViewHolder(ViewQiitaListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: QiitaListViewHolder?, position: Int) {
        list[position].also { stock ->
            holder?.bindView(stock)
        }
    }

    override fun getItemCount(): Int = list.size
}

class QiitaListViewHolder(val binding: ViewQiitaListContentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(stock: StockListResponse) {
        binding.content = stock
    }
}
