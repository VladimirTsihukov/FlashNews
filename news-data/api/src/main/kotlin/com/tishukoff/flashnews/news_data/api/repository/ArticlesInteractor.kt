package com.tishukoff.flashnews.news_data.api.repository

import com.tishukoff.flashnews.news_data.api.models.ArticlesDataState
import kotlinx.coroutines.flow.Flow

interface ArticlesInteractor {

    fun getAll(query: String): Flow<ArticlesDataState>
}