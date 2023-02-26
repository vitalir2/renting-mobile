package com.renting.app.feature.login

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.create

internal class LoginStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): LoginStore =
        object : LoginStore,
            Store<LoginStore.Intent, LoginStore.State, Nothing> by storeFactory.create(
                name = "LoginStore",
                initialState = LoginStore.State(),
                reducer = ReducerImpl,
            ) {}

    private object ReducerImpl : Reducer<LoginStore.State, LoginStore.Intent> {

        override fun LoginStore.State.reduce(msg: LoginStore.Intent): LoginStore.State =
            when (msg) {
                is LoginStore.Intent.SetLogin -> copy(login = msg.value)
                is LoginStore.Intent.SetPassword -> copy(password = msg.value)
            }
    }
}
