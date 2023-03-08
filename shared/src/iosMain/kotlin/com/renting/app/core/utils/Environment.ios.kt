package com.renting.app.core.utils

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile

actual object Environment {

    @Suppress("UNCHECKED_CAST")
    private val config: Map<String, Any?> by lazy {
        val configPath = NSBundle.mainBundle.pathForResource("EnvironmentConfig", ofType = "plist")
            ?: error("No environment config found")
        NSDictionary.dictionaryWithContentsOfFile(configPath) as Map<String, Any?>
    }

    actual val PRODUCTION_NETWORK_HOST: String by lazy {
        // TODO convert to typed config
        config["PRODUCTION_NETWORK_HOST"] as String
    }
}
