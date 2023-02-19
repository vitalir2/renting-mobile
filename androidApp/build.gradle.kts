plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.renting.app.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.renting.app.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.bundles.androidx.compose.ui.common)
    implementation(libs.androidx.compose.material2)
    implementation(libs.androidx.activity.compose)
}
