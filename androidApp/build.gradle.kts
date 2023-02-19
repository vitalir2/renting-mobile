plugins {
    id("android-app.conventions")
    kotlin("android")
}

android {
    namespace = "com.renting.app.android"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
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
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)
}
