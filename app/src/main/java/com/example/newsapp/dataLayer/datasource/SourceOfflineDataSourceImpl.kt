package com.example.newsapp.dataLayer.datasource

import com.example.newsapp.dataLayer.repository.SourcesOflineDataSource
import com.example.newsapp.dataLayer.database.SourcesDao
import com.example.newsapp.model.Source

class SourceOfflineDataSourceImpl(private val sourcesDao: SourcesDao):SourcesOflineDataSource {
    override suspend fun saveSources(source: List<Source>) {
        try{
            sourcesDao.saveSources(source)
        }
        catch (e:Exception){
            throw e
        }}

    override suspend fun getSources(category: String): List<Source> {
        return try{
            sourcesDao.getSources(category)
        }
        catch (e:Exception){
              throw e
        }
    }
}