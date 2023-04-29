package com.renting.app.feature.property.details

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.feature.property.details.PropertyDetailsStore.Intent
import com.renting.app.feature.property.details.PropertyDetailsStore.Label
import com.renting.app.feature.property.details.PropertyDetailsStore.State
import com.renting.app.feature.property.model.Property
import com.renting.app.feature.property.model.PropertyOffer

internal interface PropertyDetailsStore : Store<Intent, State, Label> {

    sealed interface Intent

    sealed interface Label

    sealed interface State {
        object Loading : State
        data class PropertyLoaded(
            val details: Property,
            val offer: PropertyOffer,
        ) : State
    }
}
