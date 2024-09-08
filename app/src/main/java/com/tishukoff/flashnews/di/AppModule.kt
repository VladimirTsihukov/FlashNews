package com.tishukoff.flashnews.di

import android.content.Context
import com.tishukoff.flashnews.BuildConfig
import com.tishukoff.flashnews.news_common.AppDispatchers
import com.tishukoff.flashnews.news_data.api.repository.ArticlesInteractor
import com.tishukoff.flashnews.news_data.impl.ArticlesInteractorImpl
import com.tishukoff.news_api.NewsApi
import com.tishukoff.news_database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient?): NewsApi {
        return NewsApi(
            baseUrl = BuildConfig.NEWS_API_BASE_URL,
            apiKey = BuildConfig.NEWS_API_KEY,
            okHttpClient = okHttpClient,
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase(context)
    }

    @Provides
    fun provideArticlesRepository(
        newsApi: NewsApi,
        newsDatabase: NewsDatabase
    ): ArticlesInteractor {
        return ArticlesInteractorImpl(newsApi, newsDatabase)
    }

    @Provides
    @Singleton
    fun provideAppDispatchers(): AppDispatchers = AppDispatchers()
}
