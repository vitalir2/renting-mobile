package com.renting.app.feature.login.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal interface LoginStore : Store<LoginStore.Intent, LoginStore.State, LoginStore.Label> {

    sealed interface Intent {
        object StartLogin : Intent
        object LoginErrorShowed : Intent
        data class SetLogin(val value: String) : Intent
        data class SetPassword(val value: String): Intent {
            override fun toString(): String = "***"
        }
    }


    sealed interface Label {
        object LoggedSuccessfully : Label
    }

    data class State(
        val login: String = "",
        val password: String = "",
        val error: String? = null,
    )
}
