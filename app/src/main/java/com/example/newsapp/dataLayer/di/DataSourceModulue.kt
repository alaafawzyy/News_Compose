package com.example.newsapp.dataLayer.di

import com.example.newsapp.dataLayer.api.NewsServices
import com.example.newsapp.dataLayer.database.NewsDataBase
import com.example.newsapp.dataLayer.datasource.SourceOfflineDataSourceImpl
import com.example.newsapp.dataLayer.datasource.SourceOnlineDataSourceImpl
import com.example.newsapp.dataLayer.repository.NetworkHandler
import com.example.newsapp.dataLayer.repository.SourcesOflineDataSource
import com.example.newsapp.dataLayer.repository.SourcesOnlineDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModulue {
    @Singleton
    @Provides
    fun provideSourceOfflineDataSource(dataBase: NewsDataBase):SourcesOflineDataSource{
        return SourceOfflineDataSourceImpl(dataBase.getSourcesDao())
    }

    @Singleton
    @Provides
    fun provideSourceOnlineDataSource(newsServices: NewsServices):SourcesOnlineDataSource{
        return SourceOnlineDataSourceImpl(newsServices)
    }

    @Provides
    fun networkHandler():NetworkHandler{
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                return true
            }
        }
    }
}