package com.renting.app.core.settings

import com.russhwolf.settings.ObservableSettings

expect class SettingsFactory {

    fun create(): ObservableSettings
}
