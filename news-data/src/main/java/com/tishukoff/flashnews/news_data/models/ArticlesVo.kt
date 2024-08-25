package com.tishukoff.flashnews.news_data.models

import java.util.Date

data class ArticlesVo(
    val source: SourceVo,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
)