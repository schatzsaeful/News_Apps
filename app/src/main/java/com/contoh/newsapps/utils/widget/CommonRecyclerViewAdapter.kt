package com.contoh.newsapps.utils.widget

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.contoh.newsapps.R

class CommonRecyclerViewAdapter<T : Any>(
    @LayoutRes private val itemLayoutRes: Int = 0,
    @LayoutRes private val loadingLayoutRes: Int = R.layout.common_item_loading,
    private val onBind: ((T, View) -> ViewBinding)? = null,
    private val onBindWithPosition: ((T, View, Int) -> ViewBinding)? = null,
    private val onClickListener: (T, Int) -> Unit = { _, _ -> },
    private val onClickListenerWithItemList: (ArrayList<T?>, T, Int) -> Unit = { _, _, _ -> }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<T?>()

    private val diffCallback: CommonDiffCallback by lazy {
        CommonDiffCallback()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] != null) {
            TYPE_ITEM
        } else {
            TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(itemLayoutRes, parent, false)

            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(loadingLayoutRes, parent, false)

            LoadingViewHolder(view)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_ITEM) {
            items[position]?.let {
                (holder as? CommonRecyclerViewAdapter<T>.ItemViewHolder)?.bind(it)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<T?>) {
        diffCallback.initList(items, newItems)
        val result = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)

        result.dispatchUpdatesTo(this)
    }

    fun showLoading() {
        items.add(null)
        notifyItemInserted(items.size)
    }

    fun hideLoading() {
        items.remove(null)
        notifyItemRemoved(items.size)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: T) {
            onBind?.invoke(data, itemView)?.root?.setOnClickListener {
                onClickListener.invoke(data, adapterPosition)
                onClickListenerWithItemList.invoke(items, data, adapterPosition)
            }

            onBindWithPosition?.invoke(data, itemView, adapterPosition)?.root?.setOnClickListener {
                onClickListener.invoke(data, adapterPosition)
                onClickListenerWithItemList.invoke(items, data, adapterPosition)
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val TYPE_ITEM = 100
        private const val TYPE_LOADING = 110
    }
}
