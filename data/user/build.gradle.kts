plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.jacoco")
    id("rembrandt.android.hilt")
}

android {
    namespace = "com.listocalixto.android.rembrandt.data.user"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(project(":common:entities"))
    implementation(project(":common:dependencies"))

    implementation(project(":core:local"))
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}
