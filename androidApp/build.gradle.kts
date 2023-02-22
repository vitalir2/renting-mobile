plugins {
    id("renting.android.app")
    alias(libs.plugins.detekt)
    kotlin("android")
}

android {
    namespace = "com.renting.app.android"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
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

    val kaspresso = "1.5.1"
    androidTestImplementation("com.kaspersky.android-components:kaspresso:$kaspresso")
    androidTestImplementation("com.kaspersky.android-components:kaspresso-compose-support:$kaspresso")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    detektPlugins(libs.detekt.ktlint)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$rootDir/config/detekt.yml")
    baseline = file("$rootDir/config/baseline.xml")
}
