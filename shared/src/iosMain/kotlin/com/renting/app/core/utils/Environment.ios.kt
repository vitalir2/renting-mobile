package com.renting.app.core.utils

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedEnvironment")
actual object Environment {

    @Suppress("UNCHECKED_CAST")
    private val config: Map<String, Any?> by lazy {
        val configPath = getConfigPath("EnvironmentConfig")
            ?: getConfigPath("DefaultEnvironmentConfig")
            ?: error("No environment config found")
        NSDictionary.dictionaryWithContentsOfFile(configPath) as Map<String, Any?>
    }

    actual val PRODUCTION_NETWORK_HOST: String by lazy {
        // TODO convert to typed config
        config["PRODUCTION_NETWORK_HOST"] as String
    }

    actual val PRODUCTION_IMAGE_HOST: String by lazy {
        config["PRODUCTION_IMAGE_HOST"] as String
    }

    private fun getConfigPath(name: String): String? {
        return NSBundle.mainBundle.pathForResource(name, ofType = "plist")
    }
}
