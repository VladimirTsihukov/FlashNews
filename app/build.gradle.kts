plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.tishukoff.flashnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tishukoff.flashnews"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "NEWS_API_KEY", "\"c04f28655a5a4818bf8b1dc8ef543ec1\"")
        buildConfigField("String", "NEWS_API_BASE_URL", "\"https://newsapi.org/v2/\"")

        resourceConfigurations += setOf("en", "ru")
        ndk {
            abiFilters += setOf("armeabi-v7a", "arm64-v8a", "x86_64")
        }
    }
    signingConfigs {
        create("release") {
            storeFile = File(rootDir, "flashNews.keystore")
            storePassword = "123456"
            keyPassword = "123456"
            keyAlias = "V.Tishukov"
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
    implementation(project(":feature:news:main"))
    implementation(project(":news-api"))
    implementation(project(":news-common"))
    implementation(project(":news-data:api"))
    implementation(project(":news-data:impl"))
    implementation(project(":news-database"))
    implementation(project(":news-ui-kit"))

    baselineProfile(":baselineprofile")

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
