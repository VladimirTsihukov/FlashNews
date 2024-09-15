plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain {
        version = JavaVersion.VERSION_21
    }

    compilerOptions {
        target {
            version = JavaVersion.VERSION_17
        }
    }
}

dependencies {
    compileOnly(libs.gradlePlugins.android)
    compileOnly(libs.gradlePlugins.kotlin)
}

gradlePlugin {
    plugins {
        register("newsKotlinLibrary") {
            id = "com.tishukoff.flashnews.kotlinLibrary"
            implementationClass = "com.tishukoff.flashnews.buildlogic.KotlinLibraryConventionPlugin"
        }
        register("newAndroidConventionPlugin") {
            id = "com.tishukoff.flashnews.androidConventionPlugin"
            implementationClass = "com.tishukoff.flashnews.buildlogic.AndroidConventionPlugin"
        }
    }
}
