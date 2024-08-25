package com.tishukoff.flashnews.news_data

import com.tishukoff.flashnews.news_data.models.ArticlesVo
import com.tishukoff.flashnews.news_data.models.toVo
import com.tishukoff.news_api.NewsApi
import com.tishukoff.news_database.NewsDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi,
) {

    fun getAll(): Flow<List<ArticlesVo>> {
        return database.articleDao
            .getAll()
            .map { articles ->
                articles.map { it.toVo() }
            }
    }
}