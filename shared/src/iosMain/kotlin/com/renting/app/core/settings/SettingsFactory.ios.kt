package com.renting.app.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {

    actual fun create(): ObservableSettings {
        val userDefaults = NSUserDefaults()
        return NSUserDefaultsSettings(userDefaults)
    }
}
