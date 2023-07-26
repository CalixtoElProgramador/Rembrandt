plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.library.jacoco")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "com.listocalixto.android.rembrandt.common.designsystem"
}

dependencies {
//    lintPublish(project(":lint"))

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.android.material)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    implementation(libs.intuit.sdp)
    implementation(libs.intuit.ssp)

//    androidTestImplementation(project(":core:testing"))
}
