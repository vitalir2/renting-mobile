package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.repository.LoginRepository
import com.renting.app.core.monad.Either
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import kotlinx.coroutines.launch

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,
    private val loginRepository: LoginRepository,
) {

    fun create(): HomeStore =
        object : HomeStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "HomeStore",
                initialState = State.Init,
                executorFactory = {
                    Executor(
                        loginRepository = loginRepository,
                    )
                },
            ) {}

    private class Executor(
        private val loginRepository: LoginRepository,
    ) : CoroutineExecutor<Intent, Nothing, State, Nothing, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Logout -> {
                    scope.launch {
                        when (loginRepository.logout()) {
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
