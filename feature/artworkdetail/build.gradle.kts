plugins {
    id("rembrandt.android.feature")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.library.jacoco")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.listocalixto.android.rembrandt.feature.artworkdetail"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":data:artwork"))
    implementation(project(":data:manifest"))
    implementation(project(":data:translator"))
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.touch.image.view)
}
