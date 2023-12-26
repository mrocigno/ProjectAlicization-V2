plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

dependencies {

    implementation(libs.core.ktx)

    api(libs.koin)
    api(libs.androidx.startup)
}