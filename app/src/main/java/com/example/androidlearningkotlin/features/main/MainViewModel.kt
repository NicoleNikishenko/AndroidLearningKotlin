package com.example.androidlearningkotlin.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlearningkotlin.models.Photo
import com.example.androidlearningkotlin.repositories.ImageListRepository

class MainViewModel(private val repo : ImageListRepository) : ViewModel()  {


    val favePhotosEvent = MutableLiveData<List<Photo>>()
    val smallPhotosEvent = MutableLiveData<List<Photo>>()
    val bigPhotosEvent = MutableLiveData<List<Photo>>()

    fun getPhotosList(){
        if (smallPhotosEvent.value.isNullOrEmpty())
            smallPhotosEvent.postValue(repo.getPhotos())

        if (bigPhotosEvent.value.isNullOrEmpty())
            bigPhotosEvent.postValue(repo.getPhotos())
    }

    fun addFavPhoto(id:Int){
        repo.addFavPhoto(id)
        favePhotosEvent.postValue(repo.favPhotos)
    }

    fun removeFavPhoto(id:Int){
        repo.removeFavPhoto(id)
        favePhotosEvent.postValue(repo.favPhotos)
    }


}
