package com.volvocars.assignment

import Dependencies
import Plugins
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class VolvoCarsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.applyPlugins()
        project.applyConfiguration()
        project.setupCommonDependencies()
    }
}

private fun Project.applyPlugins() {
    plugins.apply {
        apply(Plugins.kotlinAndroid)
        apply(Plugins.kotlinKapt)
        apply(Plugins.hilt)
        apply(Plugins.ktLint)
    }
}

private fun Project.applyConfiguration() {
    val androidExtensions = extensions.getByName("android")
    if (androidExtensions is BaseExtension) {
        androidExtensions.applyAndroidConfiguration()
        androidExtensions.applyProguardConfiguration()
    }
}

private fun BaseExtension.applyAndroidConfiguration() {

    compileSdkVersion(31)
    defaultConfig {
        if (this@applyAndroidConfiguration is AppExtension)
            applicationId = "com.volvocars.assignment"

        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/licenses/**",
                "META-INF/{AL2.0,LGPL2.1}"
            )
        )
    }
}

private fun BaseExtension.applyProguardConfiguration() {

    val proguardFiles = "proguard-rules.pro"
    when (this) {
        is LibraryExtension ->
            defaultConfig {
                consumerProguardFiles(proguardFiles)
            }
    }
}

private fun Project.setupCommonDependencies() {
    dependencies {
        implementation(Dependencies.hilt)
        kapt(Dependencies.hiltCompiler)

        testImplementation(Dependencies.jUnit)
        testImplementation(Dependencies.jUnitRules)
        testImplementation(Dependencies.coreTesting)
        testImplementation(Dependencies.mockk)
        testImplementation(Dependencies.mockk_android)
        testImplementation(Dependencies.mockito)
        testImplementation(Dependencies.nhaarman)
        testImplementation(Dependencies.liveDataTesting)
    }
}
