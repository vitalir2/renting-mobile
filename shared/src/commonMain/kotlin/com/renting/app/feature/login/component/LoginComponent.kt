package com.renting.app.feature.login.component

import com.arkivanov.decompose.value.Value
import com.renting.app.feature.login.repository.LoginError

interface LoginComponent {

    val models: Value<Model>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onLoginStarted()

    fun onLoginCompleted()

    fun onRegistrationRequested()

    fun onLoginErrorShowed()

    data class Model(
        val login: String = "",
        val password: String = "",
        val error: LoginError? = null,
    )
}
