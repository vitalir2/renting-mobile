plugins {
    id("com.android.application")
}

android {
    setupVersions()
    defaultConfig {
        applicationId = "com.renting.app"
        versionCode = 1
        versionName = "0.0.1"
        targetSdk = AndroidConstants.TARGET_SDK
    }
}
