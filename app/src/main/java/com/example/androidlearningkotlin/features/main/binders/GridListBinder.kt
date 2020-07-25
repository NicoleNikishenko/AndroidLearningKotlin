package com.example.androidlearningkotlin.features.main.binders

import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearningkotlin.R
import com.example.androidlearningkotlin.features.main.MainAdapter.PhotoAdapterListener
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class GridListBinder(private val listener: PhotoAdapterListener) : ItemBinder<GridList, GridListBinder.ViewHolder>() {


    class ViewHolder(itemView: View) : ItemViewHolder<GridList>(itemView) {
        val recyclerView : RecyclerView = itemView.findViewById(R.id.nested_rv)
    }

    override fun bindViewHolder(holder: ViewHolder, item: GridList) {

        val orientation = holder.itemView.context.resources.configuration.orientation
        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            GridLayoutManager(holder.itemView.context,8, GridLayoutManager.VERTICAL, false)
        } else {
            // In portrait
            GridLayoutManager(holder.itemView.context,4, GridLayoutManager.VERTICAL, false)
        }
        holder.recyclerView.layoutManager = layoutManager
        val adapter = PhotoSmallListAdapter(listener)
        holder.recyclerView.adapter = adapter
        adapter.submitList(item.photos)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.list_item))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is GridList
    }
}