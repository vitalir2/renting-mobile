package com.renting.app.feature.property.details

import com.arkivanov.decompose.value.Value
import com.renting.app.feature.property.model.PropertyDetails

interface PropertyDetailsComponent {

    val models: Value<Model>

    fun onBackButtonClick()

    sealed interface Model {
        object Loading : Model
        data class PropertyDetailsLoaded(
            val details: PropertyDetails,
        ) : Model
    }
}
