package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.monad.Either
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import kotlinx.coroutines.launch

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,
    private val authRepository: AuthRepository,
) {

    fun create(): HomeStore =
        object : HomeStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "HomeStore",
                initialState = State.Init,
                executorFactory = {
                    Executor(
                        authRepository = authRepository,
                    )
                },
            ) {}

    private class Executor(
        private val authRepository: AuthRepository,
    ) : CoroutineExecutor<Intent, Nothing, State, Nothing, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Logout -> {
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
            }
        }
    }
}
