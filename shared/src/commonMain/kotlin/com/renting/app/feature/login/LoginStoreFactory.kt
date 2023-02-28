package com.renting.app.feature.login

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.create
import com.renting.app.feature.login.LoginStore.Intent
import com.renting.app.feature.login.LoginStore.State

internal class LoginStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): LoginStore =
        object : LoginStore,
            Store<Intent, State, Nothing> by storeFactory.create(
                name = "LoginStore",
                initialState = State(),
                reducer = ReducerImpl,
            ) {}

    private object ReducerImpl : Reducer<State, Intent> {

        override fun State.reduce(msg: Intent): State =
            when (msg) {
                is Intent.SetLogin -> copy(login = msg.value)
                is Intent.SetPassword -> copy(password = msg.value)
            }
    }
}
