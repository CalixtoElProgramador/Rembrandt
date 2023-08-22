plugins {
    id("rembrandt.android.feature")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.library.jacoco")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.listocalixto.android.rembrandt.feature.savetocollection"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":data:collection"))
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.fragment)
}
