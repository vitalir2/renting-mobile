package com.renting.app.feature.registration

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import com.renting.app.feature.registration.RegistrationStore.State

internal class RegistrationStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): RegistrationStore =
        object : RegistrationStore, Store<Intent, State, Label> by storeFactory.create(
            name = "RegistrationStore",
            initialState = State(),
            executorFactory = {
                ExecutorImpl()
            },
            reducer = ReducerImpl(),
        ) {}

    private sealed interface Msg {
        data class Login(val login: String) : Msg
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.Login -> copy(login = login.copy(value = msg.login))
        }

    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Nothing, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SetLogin -> Msg.Login(intent.login)
                is Intent.CompleteRegistration -> {
                    // Registration request
                }
            }
        }
    }
}
