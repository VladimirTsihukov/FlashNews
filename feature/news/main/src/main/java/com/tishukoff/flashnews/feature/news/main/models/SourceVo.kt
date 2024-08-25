package com.tishukoff.flashnews.feature.news.main.models

data class SourceVo(
    val id: String,
    val name: String,
) {
    companion object {
        val EMPTY = SourceVo(
            "",
            "",
        )
    }
}
