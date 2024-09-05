package com.tishukoff.flashnews.news_data.impl.models

import com.tishukoff.flashnews.news_data.api.models.ArticlesVo
import com.tishukoff.flashnews.news_data.api.models.ErrorType
import com.tishukoff.flashnews.news_data.api.models.SourceVo
import com.tishukoff.news_api.models.ArticlesDto
import com.tishukoff.news_api.models.SourceDto
import com.tishukoff.news_database.models.ArticlesDbo
import com.tishukoff.news_database.models.SourceDbo
import java.io.IOException
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

fun ArticlesDto.toVo(): ArticlesVo {
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

fun SourceDto.toVo(): SourceVo {
    return SourceVo(
        id = id ?: "",
        name = name ?: "",
    )
}

fun SourceDbo.toVo(): SourceVo {
    return SourceVo(
        id = id ?: "",
        name = name ?: "",
    )
}

fun ArticlesDto.toDbo(): ArticlesDbo {
    return ArticlesDbo(
        id = 0,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        source = source?.toDbo(),
    )
}

fun SourceDto.toDbo(): SourceDbo {
    return SourceDbo(
        id = id,
        name = name,
    )
}

internal val Throwable.toErrorType: ErrorType
    get() = when (this) {
        is IOException -> ErrorType.Network
        else -> ErrorType.Unknown
    }