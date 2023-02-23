plugins {
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotest.multiplatform) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
