package com.tishukoff.flashnews.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroid()

            dependencies {
                implementation(libs.findLibrary("androidx-core-ktx").get())
            }
        }
    }
}
