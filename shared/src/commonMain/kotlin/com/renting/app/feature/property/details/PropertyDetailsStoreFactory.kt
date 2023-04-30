package com.renting.app.feature.property.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.monad.Either
import com.renting.app.feature.property.details.PropertyDetailsStore.Intent
import com.renting.app.feature.property.details.PropertyDetailsStore.Label
import com.renting.app.feature.property.details.PropertyDetailsStore.State
import com.renting.app.feature.property.model.OfferId
import com.renting.app.feature.property.model.PropertyId
import com.renting.app.feature.property.repository.PropertyRepository
import kotlinx.coroutines.launch
import com.renting.app.feature.property.model.PropertyDetails as DomainPropertyDetails

internal class PropertyDetailsStoreFactory(
    private val offerId: OfferId,
    private val storeFactory: StoreFactory,
    private val propertyRepository: PropertyRepository,
) {

    fun create(): PropertyDetailsStore =
        object : PropertyDetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PropertyDetails",
            initialState = State.Loading,
            bootstrapper = BootstrapperImpl(),
            executorFactory = {
                ExecutorImpl(
                    offerId = offerId,
                    propertyRepository = propertyRepository,
                )
            },
            reducer = ReducerImpl(),
        ) {}

    private sealed interface Action {
        object InitProperty : Action
    }

    private sealed interface Msg {
        data class PropertyDetails(val value: DomainPropertyDetails) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {

        override fun invoke() {
            dispatch(Action.InitProperty)
        }

    }

    private class ExecutorImpl(
        private val offerId: OfferId,
        private val propertyRepository: PropertyRepository,
    ) : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.InitProperty -> initProperty()
            }
        }

        private fun initProperty() {
            scope.launch {
                when (val result = propertyRepository.getDetails(offerId)) {
                    is Either.Left -> {
                        // TODO handle error
                    }
                    is Either.Right -> dispatch(Msg.PropertyDetails(result.value))
                }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.PropertyDetails -> State.PropertyLoaded(
                    details = msg.value,
                )
            }
        }

    }
}
