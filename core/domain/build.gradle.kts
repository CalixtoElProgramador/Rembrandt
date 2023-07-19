plugins {
    id("rembrandt.jvm.library")
}

dependencies {
    implementation(project(":common:entities"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax.inject)
}
