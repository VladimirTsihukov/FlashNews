package com.tishukoff.news_api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "status": "ok",
 * "totalResults": 498,
 * -"articles": []
 */

@Serializable
class Response<E>(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<E>,
)
