package com.tishukoff.flashnews.news_data.models

import com.tishukoff.news_database.models.ArticlesDbo
import com.tishukoff.news_database.models.SourceDbo
import java.util.Date

fun ArticlesDbo.toVo(): ArticlesVo {
    return ArticlesVo(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt ?: Date(),
        content = content ?: "",
        source = source?.toVo() ?: SourceVo("", ""),
    )
}

fun SourceDbo.toVo(): SourceVo {
    return SourceVo(
        id = id ?: "",
        name = name ?: "",
    )
}