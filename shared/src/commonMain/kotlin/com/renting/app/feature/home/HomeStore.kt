package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Store

internal interface HomeStore : Store<HomeStore.Intent, HomeStore.State, HomeStore.Label> {

    sealed interface Intent {
        object Logout : Intent
    }

    sealed interface Label {
        object LoggedOutSuccessfully : Label
    }

    sealed interface State {
        object Init : State
    }
}
