package com.renting.app.feature.property.model

import com.renting.app.core.model.Image

sealed interface Property {
    val id: PropertyId
    val location: String
    val owner: PropertyOwner
    val area: Float
    val images: List<Image>
}
