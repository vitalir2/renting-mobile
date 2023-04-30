package com.renting.app.feature.property.details.domain

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.feature.property.details.domain.PropertyDetailsStore.Intent
import com.renting.app.feature.property.details.domain.PropertyDetailsStore.Label
import com.renting.app.feature.property.details.domain.PropertyDetailsStore.State

internal interface PropertyDetailsStore : Store<Intent, State, Label> {

    sealed interface Intent

    sealed interface Label

    sealed interface State {
        object Loading : State
        data class PropertyLoaded(
            val details: PropertyDetails,
        ) : State
    }
}
