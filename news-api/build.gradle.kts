plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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