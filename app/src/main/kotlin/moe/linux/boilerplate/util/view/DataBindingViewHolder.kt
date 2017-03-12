package moe.linux.boilerplate.util.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class DataBindingViewHolder<out T : ViewDataBinding>(context: Context, resource: Int, root: ViewGroup)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(resource, root, false)) {

    val binding: T by lazy { DataBindingUtil.bind<T>(itemView) }
}
