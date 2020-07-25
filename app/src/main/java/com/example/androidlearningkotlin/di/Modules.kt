package com.example.androidlearningkotlin.di

import com.example.androidlearningkotlin.features.main.MainViewModel
import com.example.androidlearningkotlin.repositories.ImageListRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    //Defines a factory

    //Defines a singleton
    single { ImageListRepository()}

    //Defines a view model
    viewModel { MainViewModel(get()) }

}
