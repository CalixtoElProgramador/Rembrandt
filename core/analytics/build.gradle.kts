plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.compose")
    id("rembrandt.android.hilt")
}

android {
    namespace = "com.listocalixto.android.rembrandt.core.analytics"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.kotlinx.coroutines.android)
}
