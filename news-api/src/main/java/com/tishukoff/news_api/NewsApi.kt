package com.tishukoff.news_api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.tishukoff.news_api.models.ArticlesDto
import com.tishukoff.news_api.models.Language
import com.tishukoff.news_api.models.Response
import com.tishukoff.news_api.models.SortBy
import com.tishukoff.news_api.utils.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsApi {

    /**
     * API details [here] (https://newsapi.org/docs/endpoints/everything)
     */

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String? = null,
        @Query("from") data: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: List<@JvmSuppressWildcards Language>? = null,
        @Query("sortBy") sortBy: SortBy? = SortBy.RELEVANCY,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 50,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<Response<ArticlesDto>>
}

fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi = retrofit(baseUrl, apiKey, okHttpClient, json).create()

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {
    val jsonConvectorFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(modifiedOkHttpClient)
        .addConverterFactory(jsonConvectorFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()
    return retrofit
}

