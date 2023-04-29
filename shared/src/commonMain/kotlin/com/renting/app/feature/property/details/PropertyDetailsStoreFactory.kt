package com.renting.app.feature.property.details

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.feature.property.details.PropertyDetailsStore.Intent
import com.renting.app.feature.property.details.PropertyDetailsStore.Label
import com.renting.app.feature.property.details.PropertyDetailsStore.State

internal class PropertyDetailsStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): PropertyDetailsStore =
        object : PropertyDetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PropertyDetails",
            initialState = State.Loading,
            executorFactory = {
                ExecutorImpl()
            },
        ) {}

    private sealed interface Action {
        object InitProperty : Action
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Nothing, Label>() {

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.InitProperty -> initProperty()
            }
        }

        private fun initProperty() {
            // TODO
        }
    }
}
