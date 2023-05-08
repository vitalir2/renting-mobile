package com.renting.app.feature.property.model

import com.renting.app.core.model.Image

data class Apartment(
    override val id: PropertyId,
    override val location: String,
    override val owner: PropertyOwner,
    override val area: Float,
    override val images: List<Image>,
    val building: Building,
    val features: List<Feature>,
    val numberOfRooms: Int,
    val floor: Int,
    val number: String,
) : Property {

    enum class Feature {
        ELEVATOR,
    }
}
