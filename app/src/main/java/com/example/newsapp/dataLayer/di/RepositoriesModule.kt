package com.example.newsapp.dataLayer.di

import com.example.newsapp.dataLayer.repository.SourceRepositoryImpl
import com.example.newsapp.dataLayer.repository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
 abstract
class RepositoriesModule {

    @Binds
    abstract fun bindSourceRepository(impl: SourceRepositoryImpl): SourcesRepository
}