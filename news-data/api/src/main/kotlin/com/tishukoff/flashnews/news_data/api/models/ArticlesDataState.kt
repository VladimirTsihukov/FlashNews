package com.tishukoff.flashnews.news_data.api.models

sealed class ArticlesDataState {
    data object Loading : ArticlesDataState()
    data class Error(val errorType: ErrorType) : ArticlesDataState()
    data class ServerSuccess(val articles: List<ArticlesVo>) : ArticlesDataState()
    data class DatabaseSuccess(val articles: List<ArticlesVo>) : ArticlesDataState()
}
