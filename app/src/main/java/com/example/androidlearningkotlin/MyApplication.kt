package com.example.androidlearningkotlin

import android.app.Application
import com.example.androidlearningkotlin.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(appModule)
        }
    }
}