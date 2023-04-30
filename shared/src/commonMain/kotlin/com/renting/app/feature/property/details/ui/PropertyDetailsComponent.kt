package com.renting.app.feature.property.details.ui

import com.arkivanov.decompose.value.Value
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails

interface PropertyDetailsComponent {

    val models: Value<Model>

    fun onBackButtonClick()

    sealed interface Model {
        object Loading : Model
        data class PropertyDetailsLoaded(
            val details: ComponentPropertyDetails,
        ) : Model
    }
}
