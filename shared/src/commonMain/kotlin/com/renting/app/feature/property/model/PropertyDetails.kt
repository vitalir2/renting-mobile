package com.renting.app.feature.property.model

data class PropertyDetails(
    val property: Property,
    val propertyOffer: PropertyOffer,
) {
    companion object
}
