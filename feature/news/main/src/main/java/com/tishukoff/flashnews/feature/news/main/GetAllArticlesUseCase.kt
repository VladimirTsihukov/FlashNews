package com.tishukoff.flashnews.feature.news.main

import com.tishukoff.flashnews.news_data.ArticlesRepository
import com.tishukoff.flashnews.news_data.models.ArticlesVo
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(
    private val repository: ArticlesRepository,
) {

    operator fun invoke(): Flow<List<ArticlesVo>> {
       return repository.getAll()
    }
}