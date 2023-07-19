plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.library.jacoco")
    id("rembrandt.android.hilt")
    id("rembrandt.android.room")
    alias(libs.plugins.protobuf)

}

android {
    defaultConfig {
        /*testInstrumentationRunner = "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"*/
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.listocalixto.android.rembrandt.core.local"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

// Setup protobuf configuration, generating lite Java and Kotlin classes
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":common:dependencies"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.kotlinx.serialization.json)

//    testImplementation(project(":core:datastore-test"))
//    testImplementation(project(":core:testing"))
//    androidTestImplementation(project(":core:testing"))
}
