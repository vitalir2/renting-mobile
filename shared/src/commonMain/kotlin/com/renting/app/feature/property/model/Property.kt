package com.renting.app.feature.property.model

sealed interface Property {
    val id: PropertyId
    val location: String
    val owner: PropertyOwner
    val area: Float
}
