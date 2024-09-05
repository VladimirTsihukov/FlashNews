package com.tishukoff.flashnews.news_data.api.models

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
) {

    companion object {
        val LIST_FOR_PREVIEW = listOf(
            ArticlesVo(
                source = SourceVo.FOR_PREVIEW,
                author = "Author",
                title = "Title",
                description = "Description",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = Date(),
                content = "Content",
            ),
            ArticlesVo(
                source = SourceVo.FOR_PREVIEW,
                author = "Author 2",
                title = "Title 2",
                description = "Description 2",
                url = "Url",
                urlToImage = "UrlToImage",
                publishedAt = Date(),
                content = "Content",
            )
        )
    }
}