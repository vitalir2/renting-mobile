package com.renting.app.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {

    actual fun create(): Settings {
        val userDefaults = NSUserDefaults()
        return NSUserDefaultsSettings(userDefaults)
    }
}