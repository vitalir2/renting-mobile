package com.renting.app.core.settings

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(
    private val context: Context,
) {

    actual fun create(): ObservableSettings {
        val sharedPrefs = context.getSharedPreferences(SETTINGS_SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPrefs)
    }

    private companion object {
        private const val SETTINGS_SHARED_PREFS_NAME = "renting_settings"
    }
}
