package com.example.newsapp.dataLayer.repository

import com.example.newsapp.model.Source

interface SourcesRepository {

    suspend fun getSources(category:String):List<Source>
}


interface SourcesOnlineDataSource {

    suspend fun fetchSources(category:String):List<Source>
}

interface SourcesOflineDataSource {

    suspend fun saveSources(source:List<Source>)
    suspend fun getSources(category: String):List<Source>
}