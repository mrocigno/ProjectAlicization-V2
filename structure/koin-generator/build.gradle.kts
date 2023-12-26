plugins {
    //noinspection JavaPluginLanguageLevel
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {

    implementation(libs.ksp)
}