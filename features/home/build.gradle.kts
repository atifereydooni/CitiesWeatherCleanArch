plugins {
    id(Plugins.library)
    id(Plugins.volvocarsPlugin)
    id(Plugins.composePlugin)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.volvocars.home.TestAppJUnitRunner"
    }
}

dependencies{
    implementation(project(Modules.navigationModule))

    implementation(Dependencies.retrofit)
    implementation(Dependencies.interceptor)
    api(Dependencies.converter)
    api(Dependencies.moshi)
    kapt(Dependencies.moshi_kapt)

    implementation(Dependencies.composeCoil)

    kaptAndroidTest(Dependencies.hiltCompiler)
    androidTestImplementation(Dependencies.hiltAndroidTest)
    androidTestImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.composeTest)
    androidTestImplementation(Dependencies.coreTesting)
}