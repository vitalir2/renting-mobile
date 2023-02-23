plugins {
    id("renting.android.app")
    alias(libs.plugins.detekt)
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose.ui.common)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.compose.material2)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)

    androidTestImplementation(libs.bundles.androidx.test.common)
    androidTestImplementation(libs.bundles.androidx.compose.uitest)

    detektPlugins(libs.detekt.ktlint)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$rootDir/config/detekt.yml")
    baseline = file("$rootDir/config/baseline.xml")
}
