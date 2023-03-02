package com.renting.app.feature.login

import com.arkivanov.mvikotlin.core.store.Store

internal interface LoginStore : Store<LoginStore.Intent, LoginStore.State, Nothing> {

    sealed interface Intent {
        object StartLogin : Intent
        data class SetLogin(val value: String) : Intent
        data class SetPassword(val value: String): Intent {
            override fun toString(): String = "***"
        }
    }

    data class State(
        val login: String = "",
        val password: String = "",
    )
}
