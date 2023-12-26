plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

dependencies {

    implementation(libs.core.ktx)

    implementation(project(":structure:common"))
    implementation(project(":structure:koin-generator"))

    ksp(project(":structure:koin-generator"))
}