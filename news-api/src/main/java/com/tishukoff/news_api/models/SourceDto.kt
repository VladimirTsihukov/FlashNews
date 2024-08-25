package com.tishukoff.news_api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SourceDto(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
)
