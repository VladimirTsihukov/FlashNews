package com.tishukoff.flashnews.feature.news.main.models

import java.util.Date

data class ArticleVo(
    val sourceDto: SourceVo,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
) {
    companion object {
        val EMPTY = ArticleVo(
            SourceVo.EMPTY,
            "",
            "",
            "",
            "",
            "",
            Date(),
            "",
        )
    }
}
