package com.tishukoff.flashnews.news_data.api.models

sealed class ErrorType {
    data object DatabaseError: ErrorType()
    data object Network: ErrorType()
    data object Unknown: ErrorType()
}