package com.tishukoff.news_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tishukoff.news_database.dao.ArticleDao
import com.tishukoff.news_database.models.ArticlesDbo
import com.tishukoff.news_database.utils.Converters

class NewsDatabase internal constructor(
    private val database: NewsRoomDatabase,
) {

    val articleDao: ArticleDao
        get() = database.articleDao()
}

@Database(entities = [ArticlesDbo::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class NewsRoomDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}

fun NewsDatabase(applicationContext: Context): NewsDatabase {
    return NewsDatabase(
        Room.databaseBuilder(
            checkNotNull(applicationContext.applicationContext),
            NewsRoomDatabase::class.java,
            "news_database",
        ).build()
    )
}