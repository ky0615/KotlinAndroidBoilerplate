package moe.linux.boilerplate.util.view

import android.content.Context
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView


abstract class ObservableListRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(val context: Context, val list: ObservableList<T>) : RecyclerView.Adapter<VH>() {
    init {
        list.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
            override fun onChanged(p0: ObservableList<T>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(p0: ObservableList<T>?, p1: Int, p2: Int)
                = notifyItemRangeChanged(p1, p2)

            override fun onItemRangeMoved(p0: ObservableList<T>?, p1: Int, p2: Int, p3: Int)
                = notifyItemRangeChanged(p1, p2)

            override fun onItemRangeInserted(p0: ObservableList<T>?, p1: Int, p2: Int)
                = notifyItemMoved(p1, p2)

            override fun onItemRangeRemoved(p0: ObservableList<T>?, p1: Int, p2: Int)
                = notifyItemRangeRemoved(p1, p2)
        })
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): T = list[position]
}
