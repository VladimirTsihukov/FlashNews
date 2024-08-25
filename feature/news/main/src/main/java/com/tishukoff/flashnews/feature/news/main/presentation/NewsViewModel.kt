package com.tishukoff.flashnews.feature.news.main.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tishukoff.flashnews.feature.news.main.models.ArticleVo

internal class NewsViewModel(

) : ViewModel() {

    private val _articles = mutableStateOf(emptyList<ArticleVo>())
    val articles: State<List<ArticleVo>>
        get() = _articles

}