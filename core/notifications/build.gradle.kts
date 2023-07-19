plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.hilt")
}

android {
    namespace = "com.listocalixto.android.rembrandt.core.notifications"
}

dependencies {
    implementation(project(":common:entities"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.cloud.messaging)
}
