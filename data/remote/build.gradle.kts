plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

dependencies {

    implementation(libs.core.ktx)

    implementation(project(":data"))
    implementation(project(":structure:common"))
}