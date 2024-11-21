package com.example.newsapp.activity

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiManager
import com.example.newsapp.constants.Constant
import com.example.newsapp.model.Article
import com.example.newsapp.model.ArticleResponce
import com.example.newsapp.model.Source
import com.example.newsapp.model.SourcesResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    val selectedIndexState =mutableIntStateOf(0)
    val newsSources= mutableStateListOf<Source>()
    val newsStateItem =  mutableStateListOf<Article>()


    fun fetchSource(categoryId:String) {
        ApiManager.getNewsServices()
            .getNewsSources(Constant.Api_Key, categoryId)
            .enqueue(object : retrofit2.Callback<SourcesResponce> {
                override fun onResponse(
                    call: Call<SourcesResponce>,
                    response: Response<SourcesResponce>
                ) {
                    Log.e("NewsSourcesTabRow", "No ${response.body()?.sources}")
                    val sources = response.body()?.sources?.filterNotNull() ?: emptyList()

                    if (sources.isNotEmpty()) {
                        newsSources.clear()
                        newsSources.addAll(sources)

                    }
                }

                override fun onFailure(call: Call<SourcesResponce>, t: Throwable) {}

            }
            )
    }

    fun fetchNews(sourceId:String){
        ApiManager.getNewsServices()
            .getNewsArticle(Constant.Api_Key, sourceId)
            .enqueue(object : Callback<ArticleResponce> {
                override fun onResponse(
                    call: Call<ArticleResponce>,
                    response: Response<ArticleResponce>
                ) {
                    newsStateItem.clear()
                    val newsList = response.body()?.articles ?: emptyList()
                    if (newsList.isNotEmpty()) {
                        newsStateItem.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<ArticleResponce>, t: Throwable) {
                }
            })
    }


}