package com.tishukoff.news_api.models

import com.tishukoff.news_api.utils.DateTimeUTCSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Suppress("LongParameterList")
class ArticlesDto(
    @SerialName("source") val source: SourceDto?,
    @SerialName("author") val author: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String?,
    @SerialName("urlToImage") val urlToImage: String?,
    @SerialName("publishedAt") @Serializable(with = DateTimeUTCSerializer::class) val publishedAt: Date?,
    @SerialName("content") val content: String?,
)
