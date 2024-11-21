package com.example.newsapp.api

import com.example.newsapp.constants.Constant
import com.example.newsapp.model.ArticleResponce
import com.example.newsapp.model.SourcesResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//بيكون فيها ال api ال هنكلمها
interface NewsServices {
    @GET("top-headlines/sources") //اسم الفانكشن
    fun getNewsSources(
        @Query("apiKey") apikey:String=Constant.Api_Key,
    @Query("category") categoryID:String?
    ):Call<SourcesResponce>

    @GET("everything")
    fun getNewsArticle(
        @Query("apiKey") apikey:String=Constant.Api_Key,
        @Query("sources") sourceid:String
    ):Call<ArticleResponce>

    @GET("everything")
    fun getNewsArticleByQuery(
        @Query("apiKey") apikey:String=Constant.Api_Key,
        @Query("q") query:String
    ):Call<ArticleResponce>
}