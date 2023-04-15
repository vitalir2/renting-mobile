package com.renting.app.core.utils

import com.renting.app.BuildConfig

actual object Environment {

    actual val PRODUCTION_NETWORK_HOST: String
        get() = BuildConfig.PRODUCTION_NETWORK_HOST

    actual val PRODUCTION_IMAGE_HOST: String
        get() = BuildConfig.PRODUCTION_IMAGE_HOST

    actual val MODE: EnvironmentMode by lazy {
        val name = BuildConfig.ENVIRONMENT_MODE
        EnvironmentMode.createFromName(name)
    }
}
