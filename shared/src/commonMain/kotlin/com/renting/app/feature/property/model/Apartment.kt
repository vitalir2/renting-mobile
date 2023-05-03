package com.renting.app.feature.property.model

data class Apartment(
    override val id: PropertyId,
    override val location: String,
    override val owner: PropertyOwner,
    override val area: Float,
    val building: Building,
    val features: List<Feature>,
    val floor: Int,
    val number: String,
) : Property {

    enum class Feature {
        ELEVATOR,
    }
}
