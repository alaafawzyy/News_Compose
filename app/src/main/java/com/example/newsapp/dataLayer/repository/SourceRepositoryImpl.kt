package com.example.newsapp.dataLayer.repository

import com.example.newsapp.model.Source
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val onlineDataSource: SourcesOnlineDataSource,
    private val  oflineDataSource: SourcesOflineDataSource,
    private val networkHandler: NetworkHandler
):SourcesRepository {
    override suspend fun getSources(category: String): List<Source> {
      if(networkHandler.isOnline()){
          val sourceList= onlineDataSource.fetchSources(category)
          oflineDataSource.saveSources(sourceList)
          return sourceList
      }
        else
            return oflineDataSource.getSources(category)
    }
}