package com.example.androidlearningkotlin.features.main.binders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlearningkotlin.R
import com.example.androidlearningkotlin.features.main.MainAdapter.PhotoAdapterListener
import com.example.androidlearningkotlin.models.Photo
import kotlinx.android.synthetic.main.image_small_item.view.*


class PhotoSmallListAdapter(private val listener: PhotoAdapterListener) : ListAdapter <Photo, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
           return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_small_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val photo = getItem(position)
        holder.bind(photo)
        holder.photoImage.setOnClickListener{listener.onPhotoClick(photo)}
    }

    private class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.ID == newItem.ID
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImage: ImageView = itemView.photo_image_small

        fun bind(photo: Photo){
            Glide.with(itemView)
                .load(photo.url)
                .centerCrop()
                .into(photoImage)
        }
    }

}


