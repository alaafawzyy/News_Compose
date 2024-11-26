package com.example.newsapp.dataLayer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Source

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(source: List<Source>)

    @Query("SELECT * FROM  Source WHERE category=:category ")
    suspend fun getSources(category: String):List<Source>
}