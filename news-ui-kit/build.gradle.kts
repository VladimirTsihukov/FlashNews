plugins {
    alias(libs.plugins.news.android.convetion.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tishukoff.flashnews.news_ui_kit"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.material3)

    debugApi(libs.androidx.compose.ui.test.manifest)
    debugApi(libs.androidx.compose.ui.tooling)
}
