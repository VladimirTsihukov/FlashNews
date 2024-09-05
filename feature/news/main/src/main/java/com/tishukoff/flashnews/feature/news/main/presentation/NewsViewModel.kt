package com.tishukoff.flashnews.feature.news.main.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tishukoff.flashnews.feature.news.main.models.ArticleVo
import com.tishukoff.flashnews.news_common.AppDispatchers
import com.tishukoff.flashnews.news_data.api.models.ArticlesDataState
import com.tishukoff.flashnews.news_data.api.repository.ArticlesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val articlesInteractor: ArticlesInteractor,
    private val appDispatchers: AppDispatchers,
) : ViewModel() {

    private val _articles = mutableStateOf<ArticlesDataState>(ArticlesDataState.Loading)
    val articles: State<ArticlesDataState>
        get() = _articles

    init {
        viewModelScope.launch(appDispatchers.io) {
            articlesInteractor.getAll("Android").collect {
                withContext(appDispatchers.main) {
                    _articles.value = it
                }
            }
        }
    }
}