object Dependencies {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.composeAccompanist}"
    const val composeCoil = "io.coil-kt:coil-compose:${Versions.composeCoil}"

    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshi_kapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    // hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"

    // testing
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val jUnitRules = "androidx.test:rules:${Versions.jUnitRule}"
    const val barista = "com.schibsted.spain:barista:${Versions.barista}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockk_android = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val nhaarman = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.nhaarman}"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
}
