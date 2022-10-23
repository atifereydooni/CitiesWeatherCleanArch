plugins {
    id(Plugins.library)
    id(Plugins.volvocarsPlugin)
    id(Plugins.composePlugin)
}

dependencies{
    implementation(Dependencies.retrofit)
    implementation(Dependencies.interceptor)
    api(Dependencies.converter)
    api(Dependencies.moshi)
    kapt(Dependencies.moshi_kapt)
}