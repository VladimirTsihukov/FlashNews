package com.tishukoff.flashnews.news_data.impl

import android.util.Log
import com.tishukoff.flashnews.news_data.api.models.ArticlesDataState
import com.tishukoff.flashnews.news_data.api.models.ErrorType
import com.tishukoff.flashnews.news_data.api.repository.ArticlesInteractor
import com.tishukoff.flashnews.news_data.impl.models.toDbo
import com.tishukoff.flashnews.news_data.impl.models.toErrorType
import com.tishukoff.flashnews.news_data.impl.models.toVo
import com.tishukoff.news_api.NewsApi
import com.tishukoff.news_api.models.ArticlesDto
import com.tishukoff.news_database.NewsDatabase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ArticlesInteractorImpl(
    private val api: NewsApi,
    private val database: NewsDatabase,
) : ArticlesInteractor {

    companion object {
        private const val REMOVED_CONTENT = "[Removed]"
    }

    override fun getAll(query: String): Flow<ArticlesDataState> = channelFlow {
            launch {
                runCatching {
                    ArticlesDataState.DatabaseSuccess(
                        database.articleDao.getAll().map { it.toVo() })
                }.onSuccess {
                    channel.send(it)
                    Log.d("Logger", "Success getting articles from db")
                }.onFailure {
                    channel.send(ArticlesDataState.Error(ErrorType.DatabaseError))
                    Log.e("Logger", "Error getting articles from db", it)
                }
            }

            launch {
                runCatching {
                    api.getNews(query = query)
                }.onSuccess { result ->
                    result.onSuccess { freshArticles ->
                        Log.d("Logger", "Success getting articles from api")
                        savaArticles(freshArticles.articles)
                        channel.send(ArticlesDataState.ServerSuccess(
                            freshArticles.articles
                            .filter { it.title != REMOVED_CONTENT }
                            .map { it.toVo() }
                        ))
                    }.onFailure {
                        Log.e("Logger", "Error getting articles from api", it)
                        channel.send(ArticlesDataState.Error(it.toErrorType))
                    }
                }.onFailure {
                    Log.e("Logger", "Error getting articles from api", it)
                    channel.send(ArticlesDataState.Error(it.toErrorType))
                }
            }
    }.distinctUntilChanged()

    private suspend fun savaArticles(articles: List<ArticlesDto>) {
        database.articleDao.insertAll(articles.map { it.toDbo() })
    }
}