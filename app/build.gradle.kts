import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.baselineprofile)
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.tishukoff.flashnews"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.tishukoff.flashnews"
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val apiKey: String = localProperties.getProperty("NEWS_API_KEY_LOCAL") ?: ""
        val baseUrl: String = localProperties.getProperty("NEWS_API_BASE_URL_LOCAL") ?: ""

        buildConfigField("String", "NEWS_API_KEY", "\"$apiKey\"")
        buildConfigField("String", "NEWS_API_BASE_URL", "\"$baseUrl\"")

        resourceConfigurations += setOf("en", "ru")
        ndk {
            abiFilters += setOf("armeabi-v7a", "arm64-v8a", "x86_64")
        }
    }
    signingConfigs {
        val passwordLocal = localProperties.getProperty("PASSWORD_LOCAL") ?: ""
        val keyAliesLocal = localProperties.getProperty("KEY_ALIAS_LOCAL") ?: ""

        create("release") {
            storeFile = File(rootDir, "flashNews.keystore")
            storePassword = passwordLocal
            keyPassword = passwordLocal
            keyAlias = keyAliesLocal
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs["release"]
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/okhttp3/internal/publicsuffix/NOTICE"
            excludes += "/kotlin/**"
            excludes += "/META-INF/androidx.*.version"
            excludes += "/META-INF/com.google.*.version"
            excludes += "/META-INF/kotlinx_*.version"
            excludes += "/kotlin-tooling-metadata.json"
            excludes += "/DebugProbesKt.bin"
        }
    }
}

dependencies {
    implementation(projects.feature.news.main)
    implementation(projects.newsApi)
    implementation(projects.newsCommon)
    implementation(projects.newsData.api)
    implementation(projects.newsData.impl)
    implementation(projects.newsDatabase)
    implementation(projects.newsUiKit)

    baselineProfile(projects.baselineprofile)

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit)
    implementation(libs.androidx.profileinstaller)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.dagger.hilt.android)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.retrofit2)
    debugImplementation(libs.okhttp.logging.interceptor)

    kapt(libs.dagger.hilt.compiler)
}
