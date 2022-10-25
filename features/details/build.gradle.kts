plugins {
    id(Plugins.library)
    id(Plugins.volvocarsPlugin)
    id(Plugins.composePlugin)
}


android {
    defaultConfig {
        testInstrumentationRunner = "com.volvocars.details.TestAppJUnitRunner"
    }
}

dependencies{
    kaptAndroidTest(Dependencies.hiltCompiler)
    androidTestImplementation(Dependencies.hiltAndroidTest)
    androidTestImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.composeTest)
    androidTestImplementation(Dependencies.coreTesting)
}
