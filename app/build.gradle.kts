import com.listocalixto.android.rembrandt.RembrandtBuildType
import java.io.FileInputStream
import java.util.Properties


plugins {
    id("rembrandt.android.application")
    id("rembrandt.android.application.compose")
    id("rembrandt.android.application.flavors")
    id("rembrandt.android.application.jacoco")
    id("jacoco")
    // id("rembrandt.android.application.firebase")
    id("com.google.android.gms.oss-licenses-plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("rembrandt.android.hilt")
}

// Create a variable called keystorePropertiesFile, and initialize it to your
// keystore.properties file, in the rootProject folder.
val keystorePropertiesFile = project.file("$projectDir/../keystore.properties")

// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    defaultConfig {
        applicationId = "com.listocalixto.android.rembrandt"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        // Custom test runner to set up Hilt dependency graph
        // testInstrumentationRunner = "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = RembrandtBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(
                "String",
                "DEEPL_API_KEY",
                "\"${keystoreProperties["deepl.api.key"]}\"",
            )
        }
        debug {
            applicationIdSuffix = RembrandtBuildType.DEBUG.applicationIdSuffix
            buildConfigField(
                "String",
                "DEEPL_API_KEY",
                "\"${keystoreProperties["deepl.api.key"]}\"",
            )
        }
        create("benchmark") {
            // Enable all the optimizations from release build through initWith(release).
            initWith(release)
            matchingFallbacks.add("release")
            // Debug key signing is available to everyone.
            signingConfig = signingConfigs.getByName("debug")
            // Only use benchmark proguard rules
            proguardFiles("benchmark-rules.pro")
            isMinifyEnabled = true
            applicationIdSuffix = RembrandtBuildType.BENCHMARK.applicationIdSuffix
        }
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.listocalixto.android.rembrandt"
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":common:dependencies"))
    implementation(project(":common:entities"))
    implementation(project(":common:designsystem"))

    implementation(project(":core:domain"))
    implementation(project(":core:ui"))

    implementation(project(":data:artwork"))
    implementation(project(":data:user"))

    implementation(project(":feature:home"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:displayimage"))
    implementation(project(":feature:artworkdetail"))
    implementation(project(":feature:savetocollection"))

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(kotlin("test"))
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.coil.kt)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    implementation(libs.intuit.ssp)
    implementation(libs.intuit.sdp)
    implementation(libs.touch.image.view)
    implementation(libs.androidx.palette)
}

// androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
configurations.configureEach {
    resolutionStrategy {
        force(libs.junit4)
        // Temporary workaround for https://issuetracker.google.com/174733673
        force("org.objenesis:objenesis:2.6")
    }
}
