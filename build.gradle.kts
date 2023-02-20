plugins {
    // To not get errors about an implicit receiver everywhere
    val libs = libs
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    kotlin("android") version libs.versions.kotlin.get() apply false
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}
