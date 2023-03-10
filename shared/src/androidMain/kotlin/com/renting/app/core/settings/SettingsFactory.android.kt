package com.renting.app.core.settings

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

private const val SETTINGS_SHARED_PREFS_NAME = "renting_settings"

actual class SettingsFactory(
    private val context: Context,
) {

    actual fun create(): Settings {
        val sharedPrefs = context.getSharedPreferences(SETTINGS_SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPrefs)
    }
}
