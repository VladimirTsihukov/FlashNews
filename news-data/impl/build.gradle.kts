plugins {
    alias(libs.plugins.news.android.convetion.plugin)
}

android {
    namespace = "com.tishukoff.flashnews.news_data.impl"
}

dependencies {
    implementation(projects.newsDatabase)
    implementation(projects.newsApi)
    implementation(projects.newsData.api)

    implementation(libs.kotlin.coroutines.android)
}
