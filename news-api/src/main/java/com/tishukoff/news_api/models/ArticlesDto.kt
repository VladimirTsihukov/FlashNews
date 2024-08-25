package com.tishukoff.news_api.models

import com.tishukoff.news_api.utils.DateTimeUTCSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * "source": {
 * "id": "techcrunch",
 * "name": "TechCrunch"
 * },
 * "author": "Amanda Silberling",
 * "title": "Watch: Tech billionaires want to build a utopian city in California",
 * "description": "The California Forever project is on hold until 2026. The family of Marc Andreessen is planning to build another large community instead.",
 * "url": "https://techcrunch.com/video/techcrunch-minute-tech-billionaires-want-to-build-a-utopian-city-in-california/",
 * "urlToImage": "https://techcrunch.com/wp-content/uploads/2024/08/YouTube-Thumb-Text-3-3.png?resize=1200,675",
 * "publishedAt": "2024-08-24T12:01:22Z",
 * "content": "A crew of tech billionaires have been trying to get the California Forever project off the ground, which would create a new, supposedly utopian city on land north of the Bay Area. That project is on … [+751 chars]"
 * }
 */
@Serializable
class ArticlesDto (
    @SerialName("source") val sourceDto: SourceDto?,
    @SerialName("author") val author: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String?,
    @SerialName("urlToImage") val urlToImage: String?,
    @SerialName("publishedAt") @Serializable(with = DateTimeUTCSerializer::class) val publishedAt: Date?,
    @SerialName("content") val content: String?,
)
