package com.tishukoff.flashnews.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configureAndroid()

            dependencies {
                implementation(project(":news-ui-kit"))

                implementation(platform(libs.findLibrary("androidx-compose-bom").get()))

                implementation(libs.findLibrary("androidx-core-ktx").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                implementation(libs.findLibrary("androidx-material3").get())

            }
        }
    }
}
