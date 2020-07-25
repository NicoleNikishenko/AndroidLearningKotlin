package com.example.androidlearningkotlin.features.main

import com.example.androidlearningkotlin.features.main.binders.*
import com.example.androidlearningkotlin.models.Photo
import mva2.adapter.HeaderSection
import mva2.adapter.ItemSection
import mva2.adapter.MultiViewAdapter


class MainAdapter(
    photoSmallListener: PhotoAdapterListener)
                    : MultiViewAdapter() {

    interface PhotoAdapterListener {
        fun onPhotoClick(photo: Photo) {}
    }

    private val favoriteSection:ItemSection<HorizontalList>
    private val horizontalSection: ItemSection<HorizontalList>
    private val gridSection: ItemSection<GridList>


    init {

        // Register Binder
       registerItemBinders(HeaderBinder(),  HorizontalListBinder(photoSmallListener) , GridListBinder(photoSmallListener))

        // Favorite Section
        val favoriteHeaderSection = HeaderSection<String>()
        favoriteHeaderSection.header = "Favorite List"
        favoriteSection = ItemSection()
        favoriteHeaderSection.addSection(favoriteSection)

        //First Section
        val horizontalHeaderSection = HeaderSection<String>()
        horizontalHeaderSection.header = "Horizontal List"
        horizontalSection = ItemSection()
        horizontalHeaderSection.addSection(horizontalSection)

        //Second Section
        val gridHeaderSection = HeaderSection<String>()
        gridHeaderSection.header = "Vertical List"
        gridSection = ItemSection()
        gridHeaderSection.addSection(gridSection)

        // Add Section to the adapter
        addSection(favoriteHeaderSection)
        addSection(horizontalHeaderSection)
        addSection(gridHeaderSection)

    }

    fun updateListData(list:Any){
        when(list){
            is HorizontalList -> {
                horizontalSection.setItem(list)
            }
            is GridList -> {
                gridSection.setItem(list)
            }
        }

    }
    fun updateFavListData(list:HorizontalList){
        favoriteSection.setItem(list)
    }
}