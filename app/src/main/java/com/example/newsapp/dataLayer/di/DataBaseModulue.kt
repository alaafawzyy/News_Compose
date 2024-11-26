package com.example.newsapp.dataLayer.di

import com.example.newsapp.dataLayer.database.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModulue {

    @Singleton
    @Provides
    fun provideNewsDataBase():NewsDataBase{
        return NewsDataBase.getInctance()
    }
}