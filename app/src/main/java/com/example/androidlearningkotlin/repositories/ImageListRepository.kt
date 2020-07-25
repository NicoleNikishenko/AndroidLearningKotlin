package com.example.androidlearningkotlin.repositories

import com.example.androidlearningkotlin.models.Photo

class ImageListRepository (private var imageCount : Int = 0) {

    var favPhotos = mutableListOf<Photo>()
    var allPhotosList = mutableListOf<Photo>()

    fun getPhotos(): List<Photo> {
        val arraySize = imageCount + 40
        val photos = mutableListOf<Photo>()
        for (i in imageCount until arraySize){
            val photo = Photo(i, "https://picsum.photos/900?temp=$i")
            imageCount++
            allPhotosList.add(photo)
            photos.add(photo)
        }
        return photos
    }
    fun addFavPhoto(id: Int){
        val photo = allPhotosList.find{it.ID == id}
        photo!!.favorite = true
        favPhotos.add(photo)
    }
    fun removeFavPhoto(id:Int){
        val photo = favPhotos.find{it.ID == id}
        photo!!.favorite = false
        favPhotos.remove(photo)
    }
}