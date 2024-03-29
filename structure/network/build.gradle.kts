plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
}

dependencies {

    implementation(libs.core.ktx)
    implementation(project(":structure:common"))

    implementation(libs.lifecycle.runtime.compose.ktx)
}