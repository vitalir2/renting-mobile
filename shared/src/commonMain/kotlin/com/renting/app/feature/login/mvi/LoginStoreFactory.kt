package com.renting.app.feature.login.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.monad.Either
import com.renting.app.feature.login.mvi.LoginStore.Intent
import com.renting.app.feature.login.mvi.LoginStore.State
import com.renting.app.feature.login.repository.LoginRepository
import kotlinx.coroutines.launch

internal class LoginStoreFactory(
    private val storeFactory: StoreFactory,
    private val loginRepository: LoginRepository,
) {

    fun create(): LoginStore =
        object : LoginStore,
            Store<Intent, State, Nothing> by storeFactory.create(
                name = "LoginStore",
                initialState = State(),
                reducer = ReducerImpl,
                executorFactory = {
                    ExecutorImpl(
                        loginRepository = loginRepository,
                    )
                },
            ) {}

    private sealed interface Msg {
        data class Login(val value: String) : Msg
        data class Password(val value: String) : Msg
        data class LoggedIn(val token: String) : Msg
        data class Error(val message: String?) : Msg
    }

    private object ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.Login -> copy(login = msg.value)
                is Msg.Password -> copy(password = msg.value)
                is Msg.LoggedIn -> copy(token = msg.token)
                is Msg.Error -> copy(error = msg.message)
            }
    }

    private class ExecutorImpl(
        private val loginRepository: LoginRepository,
    ) : CoroutineExecutor<Intent, Nothing, State, Msg, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SetLogin -> dispatch(Msg.Login(intent.value))
                is Intent.SetPassword -> dispatch(Msg.Password(intent.value))
                is Intent.StartLogin -> {
                    val state = getState()
                    scope.launch {
                        val result = loginRepository.login(
                            login = state.login,
                            password = state.password,
                        )
                        when (result) {
                            is Either.Left -> dispatch(Msg.Error(result.error.message))
                            is Either.Right -> dispatch(Msg.LoggedIn(result.value))
                        }
                    }
                }
            }
        }
    }
}
