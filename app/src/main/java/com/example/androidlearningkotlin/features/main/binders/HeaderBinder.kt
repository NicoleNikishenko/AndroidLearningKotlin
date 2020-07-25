package com.example.androidlearningkotlin.features.main.binders

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidlearningkotlin.R
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HeaderBinder : ItemBinder<String, HeaderBinder.ViewHolder>() {


    class ViewHolder(itemView: View) : ItemViewHolder<String>(itemView) {
        val header: TextView = itemView.findViewById(R.id.header_title)
    }

    override fun bindViewHolder(holder: ViewHolder, item: String?) {
        holder.header.text = item
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(inflate(parent!!, R.layout.header_item))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is String
    }
}
