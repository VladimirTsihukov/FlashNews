package com.tishukoff.news_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tishukoff.news_database.models.ArticlesDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticlesDbo>>

    @Insert
    suspend fun insertAll(articles: List<ArticlesDbo>)

    @Query("DELETE FROM articles")
    suspend fun clear()
}