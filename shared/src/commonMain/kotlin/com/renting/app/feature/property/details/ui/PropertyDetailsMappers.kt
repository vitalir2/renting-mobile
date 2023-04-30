package com.renting.app.feature.property.details.ui

import com.renting.app.feature.property.details.ui.PropertyDetailsComponent.Model
import com.renting.app.feature.property.details.domain.PropertyDetailsStore.State
import com.renting.app.feature.property.details.ui.model.toUiModel

internal object PropertyDetailsMappers {

    val stateToModel: State.() -> Model = {
        when (this) {
            is State.Loading -> Model.Loading
            is State.PropertyLoaded -> Model.PropertyDetailsLoaded(
                details = details.toUiModel(),
            )
        }
    }
}
