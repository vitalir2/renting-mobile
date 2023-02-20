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
    compileOnly("com.android.library:com.android.library.gradle.plugin:7.3.1")
}
gradlePlugin {
    plugins {
        register("androidApp") {
            id = "renting.android.app"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLib") {
            id = "renting.android.lib"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
