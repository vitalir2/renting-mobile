repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

// TODO: Replace the deps versions by the ones from the version catalog
@Suppress("GradleDependency")
dependencies {
    implementation("com.android.library:com.android.library.gradle.plugin:7.3.1")
}
