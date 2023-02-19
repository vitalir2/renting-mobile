plugins {
    // To not get errors about an implicit receiver everywhere
    val libs = libs
    kotlin("android") version libs.versions.kotlin.get() apply false
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
