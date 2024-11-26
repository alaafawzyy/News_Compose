package com.example.newsapp.activity

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.dataLayer.api.NewsServices
import com.example.newsapp.dataLayer.repository.SourcesRepository
import com.example.newsapp.model.Article
import com.example.newsapp.model.ArticleResponce
import com.example.newsapp.model.Source
import com.example.newsapp.model.SourcesResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val sourcesRepository: SourcesRepository,
    private val newsServices: NewsServices
):ViewModel() {
    val selectedIndexState =mutableIntStateOf(0)
    val newsSources= mutableStateListOf<Source>()
    val newsStateItem =  mutableStateListOf<Article>()

    fun fetchSource(categoryId:String) {
        viewModelScope.launch { try {
            val response = sourcesRepository.getSources(categoryId)
            if (response.isNotEmpty()==true) {
                newsSources.clear()
                Log.w("news", "alaa $response")
                newsSources.addAll(response.filterNotNull())
            }
        }catch (e:Exception){
            Log.e("news", "error to get surces $e")
        }
        }
    }

    fun fetchNews(sourceId:String) {
        viewModelScope.launch() {
            try {
                val response = newsServices.getNewsArticle(sourceid=sourceId)

                newsStateItem.clear()
                if (response.articles?.isNotEmpty() == true) {
                    newsStateItem.addAll(response.articles.distinct())
                }
            } catch (e: Exception) {
                Log.e("news", "error toget news $e")
            }
        }

    }


}