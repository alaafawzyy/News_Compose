package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    @PrimaryKey(autoGenerate = true)
    val sourcePrimarykey: Int?,

    val category: String?,
    val country: String?,
    val description: String?,
    val id: String?,
    val language: String?,
    val name: String?,
    val url: String?
)