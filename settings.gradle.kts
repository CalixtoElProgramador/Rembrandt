pluginManagement {
    includeBuild("build-logic")
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
        maven("https://jitpack.io")
    }
}

rootProject.name = "Rembrandt"
include(":app")

include(":feature:home")
include(":feature:artworkdetail")
include(":feature:displayimage")
include(":feature:explore")
include(":feature:favorites")

include(":data:artwork")
include(":data:collection")
include(":data:manifest")
include(":data:translator")
include(":data:user")

include(":core:analytics")
include(":core:domain")
include(":core:local")
include(":core:network")

include(":core:notifications")
include(":core:ui")
include(":common:entities")
include(":common:designsystem")
include(":common:dependencies")
include(":common:utility")
include(":feature:savetocollection")
