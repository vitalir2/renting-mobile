package com.renting.app.feature.login.component

import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val models: Value<Model>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onLoginStarted()

    fun onLoginCompleted()

    fun onRegistrationRequested()

    data class Model(
        val login: String = "",
        val password: String = "",
        val error: String? = null,
    )
}
