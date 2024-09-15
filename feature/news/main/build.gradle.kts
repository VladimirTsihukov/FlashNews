plugins {
    alias(libs.plugins.news.android.compose.convetion.plugin)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.tishukoff.flashnews.feature.news.main"
}

dependencies {
    implementation(projects.newsCommon)
    implementation(projects.newsData.api)

    implementation(libs.coil.compose)
    implementation(libs.coil.core)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}
