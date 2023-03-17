package com.renting.app.feature.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.renting.app.core.utils.stateAsValue
import com.renting.app.core.validation.TextField
import com.renting.app.feature.registration.RegistrationComponent.Model
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class DefaultRegistrationComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    registrationGraph: RegistrationGraph,
    private val onRegistrationCompleted: () -> Unit,
) : RegistrationComponent, ComponentContext by componentContext, RegistrationGraph by registrationGraph {

    private val store = instanceKeeper.getStore {
        RegistrationStoreFactory(
            storeFactory = storeFactory,
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

    override fun completeRegistration() {
        store.accept(Intent.CompleteRegistration)
    }
}
