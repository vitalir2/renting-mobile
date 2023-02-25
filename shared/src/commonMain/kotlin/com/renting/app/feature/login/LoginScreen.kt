package com.renting.app.feature.login

import com.arkivanov.mvikotlin.core.view.MviView

interface LoginScreen : MviView<LoginScreen.Model, LoginScreen.Event> {

    data class Model(
        val login: String,
        val password: String,
    )

    sealed interface Event {
        data class LoginInputChanged(val value: String) : Event
        data class PasswordInputChanged(val value: String) : Event {
            override fun toString(): String = "***"
        }
    }
}
