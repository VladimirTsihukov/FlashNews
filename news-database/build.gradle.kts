plugins {
    alias(libs.plugins.news.android.convetion.plugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tishukoff.news_database"
}

dependencies {
    implementation(libs.androidx.room.ktx)

    ksp(libs.androidx.room.compiler)
}
