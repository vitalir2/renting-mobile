package com.renting.app.feature.registration.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.renting.app.core.utils.stateAsValue
import com.renting.app.core.form.TextField
import com.renting.app.feature.registration.component.RegistrationComponent.Model
import com.renting.app.feature.registration.di.RegistrationGraph
import com.renting.app.feature.registration.mvi.RegistrationStore.Intent
import com.renting.app.feature.registration.mvi.RegistrationStore.Label
import com.renting.app.feature.registration.mvi.RegistrationStoreFactory
import com.renting.app.feature.registration.mvi.RegistrationStoreMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class DefaultRegistrationComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    registrationGraph: RegistrationGraph,
    private val onRegistrationCompleted: () -> Unit,
    private val openLoginScreen: () -> Unit,
) : RegistrationComponent, ComponentContext by componentContext, RegistrationGraph by registrationGraph {

    private val store = instanceKeeper.getStore {
        RegistrationStoreFactory(
            storeFactory = storeFactory,
            registrationGraph = registrationGraph,
        ).create()
    }

    init {
        store.labels
            .onEach { label -> handleLabel(label) }
            .launchIn(coroutineScope)
    }

    private fun handleLabel(label: Label) {
        when (label) {
            is Label.RegistrationCompleted -> onRegistrationCompleted()
        }
    }

    override val models: Value<Model> = store.stateAsValue()
        .map(RegistrationStoreMapper.stateToModel)

    override fun onFieldChanged(id: TextField.Id, value: String) {
        store.accept(Intent.SetFieldValue(id, value))
    }

    override fun onLoginRequired() {
        openLoginScreen()
    }

    override fun completeRegistration() {
        store.accept(Intent.CompleteRegistration)
    }
}
