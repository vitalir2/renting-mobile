package com.renting.app.feature.login

import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val models: Value<LoginScreen.Model>

    fun onLoginInputChanged(login: String)

    fun onPasswordInputChanged(password: String)
}
