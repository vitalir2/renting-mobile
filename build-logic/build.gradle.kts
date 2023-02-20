repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle)
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
