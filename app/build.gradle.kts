import com.volvocars.assignment.implementation
import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.application)
    id(Plugins.volvocarsPlugin)
    id(Plugins.composePlugin)
}

val keystorePropertiesFile = rootProject.file("keys/release/keystore.properties")
val keystoreProperties = Properties().apply {
    if (keystorePropertiesFile.exists())
        load(FileInputStream(keystorePropertiesFile))
}

android {
    signingConfigs {
        create("release") {
            storeFile = (keystoreProperties["storeFile"] as String?)?.let { file(it) }
            storePassword = keystoreProperties["storePassword"] as String?
            keyAlias = keystoreProperties["keyAlias"] as String?
            keyPassword = keystoreProperties["keyPassword"] as String?
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig =
                signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation(project(Modules.navigationModule))
    implementation(project(Modules.homeModule))
}
