package com.example.newsapp.dataLayer.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging= HttpLoggingInterceptor{
                Log.e("api",it) }
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return logging
        }
        @Provides
        fun provideOkHttpclinet(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create()
        }
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }

        @Provides
        fun provideWebServices(retrofit: Retrofit): NewsServices {
            Log.w("a","alaa")
            return retrofit.create(NewsServices::class.java)

        }
    }