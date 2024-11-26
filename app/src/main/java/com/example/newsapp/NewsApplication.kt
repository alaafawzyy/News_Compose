package com.example.newsapp

import android.app.Application
import com.example.newsapp.dataLayer.database.NewsDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDataBase.init(this)
    }
}