plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.library.jacoco")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.listocalixto.android.rembrandt.core.ui"
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    /*api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)*/
    api(libs.androidx.metrics)
    api(libs.androidx.tracing.ktx)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(project(":common:designsystem"))
    implementation(project(":common:entities"))
    implementation(libs.androidx.browser)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.appcompat)
    api(libs.android.material)
    implementation(libs.androidx.palette)
    implementation(libs.intuit.ssp)
    implementation(libs.intuit.sdp)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    implementation(libs.touch.image.view)

//    androidTestImplementation(project(":core:testing"))
}
