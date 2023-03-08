import com.android.build.api.dsl.LibraryDefaultConfig
import java.util.*

plugins {
    id("renting.android.lib")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotest.multiplatform)
    id("kotlin-parcelize")
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
            export(libs.mvikotlin.main)
            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    sourceSets {
        val ktorVersion = "2.2.3"
        val kotlinxSerializationVersion = "1.5.0"

        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines.core)
                api(libs.mvikotlin.core)
                api(libs.mvikotlin.main)
                api(libs.mvikotlin.rx)
                implementation(libs.mvikotlin.extensions.coroutines)
                implementation(libs.mvikotlin.logging)

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

                api(libs.decompose)
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
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
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

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
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

inline fun <reified T : Any> LibraryDefaultConfig.addBuildConfigProperty(name: String, value: T) {
    val (fieldType, fieldValue) = when (T::class) {
        String::class -> "String" to "\"$value\""
        else -> error("Unknown property type=${T::class}")
    }
    buildConfigField(fieldType, name, fieldValue)
}

android {
    namespace = "com.renting.app"

    defaultConfig {
        val androidLocalProps = Properties().apply {
            load(project.rootDir.resolve("local.properties").inputStream())
        }

        addBuildConfigProperty<String>(
            name = "PRODUCTION_NETWORK_HOST",
            value = androidLocalProps.getProperty("renting.production.network.host"),
        )
    }
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
