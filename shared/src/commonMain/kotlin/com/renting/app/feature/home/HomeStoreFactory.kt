package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.UserRepository
import com.renting.app.core.monad.Either
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import kotlinx.coroutines.launch

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {

    fun create(): HomeStore =
        object : HomeStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "HomeStore",
                initialState = State(),
                executorFactory = {
                    ExecutorImpl(
                        authRepository = authRepository,
                        userRepository = userRepository,
                    )
                },
                bootstrapper = BootstrapperImpl(),
                reducer = ReducerImpl(),
            ) {}

    private sealed interface Action {
        object InitProfile : Action
    }

    private sealed interface Msg {
        data class UserInfoReceived(val userInfo: UserInfo) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {

        override fun invoke() {
            dispatch(Action.InitProfile)
        }
    }

    private class ExecutorImpl(
        private val authRepository: AuthRepository,
        private val userRepository: UserRepository,
    ) : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Logout -> logout()
            }
        }

        private fun logout() {
            scope.launch {
                when (authRepository.logout()) {
                    is Either.Left -> {
                        // Nothing for now
                    }
                    is Either.Right -> {
                        publish(Label.LoggedOutSuccessfully)
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.InitProfile -> initProfile()
            }
        }

        private fun initProfile() {
            scope.launch {
                when (val userInfo = userRepository.getUserInfo()) {
                    is Either.Left -> {
                        // Nothing for now
                    }
                    is Either.Right -> dispatch(Msg.UserInfoReceived(userInfo.value))
                }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.UserInfoReceived -> copy(userInfo = msg.userInfo)
            }
        }
    }
}
