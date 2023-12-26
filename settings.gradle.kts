@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Alicization"

include(":app")

include(":structure")
include(":structure:network")
include(":structure:koin-generator")
include(":structure:common")

include(":data")
include(":data:local")
include(":data:remote")