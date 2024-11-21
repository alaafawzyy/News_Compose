package com.example.newsapp.model

data class ArticleResponce(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)