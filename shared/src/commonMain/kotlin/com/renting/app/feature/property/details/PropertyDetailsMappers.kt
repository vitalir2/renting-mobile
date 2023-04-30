package com.renting.app.feature.property.details

import com.renting.app.feature.property.details.PropertyDetailsComponent.Model
import com.renting.app.feature.property.details.PropertyDetailsStore.State

internal object PropertyDetailsMappers {

    val stateToModel: State.() -> Model = {
        when (this) {
            is State.Loading -> Model.Loading
            is State.PropertyLoaded -> Model.PropertyDetailsLoaded(
                details = details,
            )
        }
    }
}
