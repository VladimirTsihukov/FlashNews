package com.tishukoff.flashnews.news_data.api.models

data class SourceVo(
    val id: String,
    val name: String,
) {
    companion object {
        val FOR_PREVIEW = SourceVo(
            id = "Id",
            name = "Name",
        )
    }
}
