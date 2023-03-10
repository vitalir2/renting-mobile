package com.renting.app.core.settings

import com.russhwolf.settings.Settings

expect class SettingsFactory {

    fun create(): Settings
}
