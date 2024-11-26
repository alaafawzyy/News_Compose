package com.example.newsapp.dataLayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.Source

@Database(entities = [Source::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
 abstract fun getSourcesDao(): SourcesDao

    companion object{
        private var INCTANCE:NewsDataBase?=null
        private val DATABASE_NAME="news database"
        fun init(context: Context){
            if (INCTANCE==null){
                INCTANCE= Room.databaseBuilder(context, NewsDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        fun getInctance():NewsDataBase{
            return INCTANCE!!
        }

    }
}