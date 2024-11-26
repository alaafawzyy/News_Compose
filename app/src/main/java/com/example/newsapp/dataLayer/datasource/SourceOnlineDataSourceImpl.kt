package com.example.newsapp.dataLayer.datasource

import com.example.newsapp.dataLayer.api.NewsServices
import com.example.newsapp.dataLayer.repository.SourcesOnlineDataSource
import com.example.newsapp.model.Source

class SourceOnlineDataSourceImpl(private val newsServices: NewsServices):SourcesOnlineDataSource {
    override suspend fun fetchSources(category: String): List<Source> {
      return try {
          newsServices.getNewsSources(categoryID = category).sources?.filterNotNull() ?: listOf()
      }
      catch (e:Exception){
          throw e
      }
    }
}