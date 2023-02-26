package com.renting.app.feature.login

import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val models: Value<Model>

    fun onLoginInputChanged(login: String)

    fun onPasswordInputChanged(password: String)

    data class Model(
        val login: String = "",
        val password: String = "",
    )
}
