plugins {
    id("rembrandt.android.feature")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.library.jacoco")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.listocalixto.android.rembrandt.feature.displayimage"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.touch.image.view)
}
