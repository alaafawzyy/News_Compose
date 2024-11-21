package com.example.newsapp.api

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//علشان نعمل امبليمنتيشن لل انترفيس ومش انا ال بعمل دي ريتروفيت
object ApiManager {
    private val httpLoggingInterceptor=HttpLoggingInterceptor{
        Log.e("api",it)
    }
        .apply { level= HttpLoggingInterceptor.Level.BODY }
    private val okHttpClient=OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
    //create instance
    private val retrofit=Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client( okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsServices():NewsServices{
        return retrofit.create(NewsServices::class.java)
    }
}