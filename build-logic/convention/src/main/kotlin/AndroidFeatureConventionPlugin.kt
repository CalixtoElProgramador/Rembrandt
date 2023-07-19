import com.android.build.gradle.LibraryExtension
import com.listocalixto.android.rembrandt.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("rembrandt.android.library")
                apply("androidx.navigation.safeargs.kotlin")
                apply("rembrandt.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    /*testInstrumentationRunner = "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"*/
                }
                configureGradleManagedDevices(this)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":common:dependencies"))
                add("implementation", project(":common:entities"))
                add("implementation", project(":common:designsystem"))

                add("implementation", project(":core:analytics"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:ui"))

                add("testImplementation", kotlin("test"))
//                add("testImplementation", project(":core:testing"))
                add("androidTestImplementation", kotlin("test"))
//                add("androidTestImplementation", project(":core:testing"))

                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.activity").get())
                add("implementation", libs.findLibrary("androidx.fragment").get())
                add("implementation", libs.findLibrary("androidx.navigation.ui").get())
                add("implementation", libs.findLibrary("androidx.navigation.fragment").get())
                add("implementation", libs.findLibrary("androidx.navigation.dynamic.features.fragment").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}
