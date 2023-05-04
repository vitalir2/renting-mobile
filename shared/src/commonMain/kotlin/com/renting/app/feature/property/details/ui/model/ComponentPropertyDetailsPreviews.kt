package com.renting.app.feature.property.details.ui.model

import com.renting.app.feature.property.details.domain.PropertyDetailsPreviews

object ComponentPropertyDetailsPreviews {
    val familyHouse get() = PropertyDetailsPreviews.familyHouse.toUiModel()

    val apartment get() = PropertyDetailsPreviews.apartment.toUiModel()

    val land get() = PropertyDetailsPreviews.land.toUiModel()
}
