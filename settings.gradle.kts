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

rootProject.name = "CamApp"
include(":app")
include(":server_list")
project(":server_list").projectDir = file("./features/server_list")
include(":navigation")
project(":navigation").projectDir = file("./features/navigation")
include(":data")
include(":domain")
