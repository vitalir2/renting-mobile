package com.renting.app.core.utils

import com.renting.app.BuildConfig

actual object Environment {

    actual val PRODUCTION_NETWORK_HOST: String
        get() = BuildConfig.PRODUCTION_NETWORK_HOST
}
