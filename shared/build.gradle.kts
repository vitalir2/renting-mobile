plugins {
    id("renting.android.lib")
    kotlin("multiplatform")
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotest.multiplatform)
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // TODO to the version catalog
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                implementation(libs.mvikotlin.core)
                implementation(libs.mvikotlin.main)
                implementation(libs.mvikotlin.extensions.coroutines)
                implementation(libs.mvikotlin.logging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.renting.app"
}

// TODO add to convenience plugin
detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$rootDir/config/detekt.yml")
    baseline = file("$rootDir/config/baseline.xml")
}

internal val platformNames = listOf(
    "common",
    "android",
    "ios",
)

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektMultiplatform") {
    description = "Runs a mutliplatform lint check"
    setSource(
        files(
            *platformNames.map { name -> "src/${name}Main/kotlin" }.toTypedArray(),
            *platformNames.map { name -> "src/${name}Test/kotlin" }.toTypedArray(),
        )
    )
    config.setFrom(files("$rootDir/config/detekt.yml"))
    reports {
        txt.required.set(false)
        sarif.required.set(false)
        xml.required.set(false)
        md.required.set(false)
        html.required.set(true)
        html.outputLocation.set(File("build/reports/detekt/detekt.html"))
    }
    include("**/*.kt")
    include("**/*.kts")
    exclude("build/")
}

android.testOptions {
    unitTests.all {
        it.useJUnitPlatform()
    }
}
