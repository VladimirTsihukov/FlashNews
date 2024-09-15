plugins {
    alias(libs.plugins.news.kotlin.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

dependencies {
    implementation(libs.retrofit2)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.annotation)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit2.adapters.result)
    implementation(libs.okhttp.logging.interceptor)
}
