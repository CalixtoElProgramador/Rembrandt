plugins {
    id("rembrandt.android.library")
    id("rembrandt.android.hilt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "com.listocalixto.android.rembrandt.common.utility"
}

dependencies {
}
