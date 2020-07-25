package com.example.androidlearningkotlin.features.main.binders


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearningkotlin.R
import com.example.androidlearningkotlin.features.main.MainAdapter.PhotoAdapterListener
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HorizontalListBinder(private val listener: PhotoAdapterListener) : ItemBinder<HorizontalList, HorizontalListBinder.ViewHolder>() {

    override fun bindViewHolder(holder: ViewHolder, item: HorizontalList) {
        val layoutManager = LinearLayoutManager(holder.itemView.context ,LinearLayoutManager.HORIZONTAL ,false)
        holder.recyclerView.layoutManager = layoutManager
        val adapter = PhotoBigListAdapter(listener)
        holder.recyclerView.adapter = adapter
        adapter.submitList(item.photos)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.list_item))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is HorizontalList
    }

    class ViewHolder(itemView: View) : ItemViewHolder<HorizontalList>(itemView) {
        val recyclerView : RecyclerView = itemView.findViewById(R.id.nested_rv)
    }
}
