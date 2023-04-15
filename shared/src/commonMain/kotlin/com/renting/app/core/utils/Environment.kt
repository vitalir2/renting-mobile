package com.renting.app.core.utils

expect object Environment {

    val MODE: EnvironmentMode

    val PRODUCTION_NETWORK_HOST: String

    val PRODUCTION_IMAGE_HOST: String
}

enum class EnvironmentMode(val stringName: String) {
    DEVELOPMENT("dev"),
    PRODUCTION("prod"),
    ;

    val isDevelopment: Boolean
        get() = this === DEVELOPMENT

    companion object {
        fun createFromName(name: String): EnvironmentMode {
            return EnvironmentMode.values().first { it.stringName == name }
        }
    }
}
