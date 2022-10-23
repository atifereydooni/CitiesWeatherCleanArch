package com.volvocars.assignment

import Dependencies
import Versions
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.enableCompose()
        project.setupComposeDependencies()
    }
}

private fun Project.enableCompose() {
    val androidExtensions = extensions.getByName("android")
    if (androidExtensions is BaseExtension) {
        androidExtensions.apply {
            buildFeatures.apply {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = Versions.compose
            }
        }
    }
}

private fun Project.setupComposeDependencies() {
    dependencies {
        implementation(Dependencies.material)
        implementation(Dependencies.composeUi)
        implementation(Dependencies.composeMaterial)
        implementation(Dependencies.composeActivity)
        implementation(Dependencies.accompanistNavigationAnimation)
        implementation(Dependencies.composePreview)
        implementation(Dependencies.composeRuntime)
        implementation(Dependencies.hiltNavigation)

        androidTestImplementation(Dependencies.espresso)
        androidTestImplementation(Dependencies.jUnitExt)
        androidTestImplementation(Dependencies.barista)
        androidTestImplementation(Dependencies.composeTest)
        androidTestImplementation(Dependencies.hiltAndroidTest)

        debugImplementation(Dependencies.composeTooling)
        debugImplementation(Dependencies.composeManifest)
    }
}

