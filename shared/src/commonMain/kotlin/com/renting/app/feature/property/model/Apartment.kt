package com.renting.app.feature.property.model

data class Apartment(
    override val id: PropertyId,
    override val location: String,
    override val owner: PropertyOwner,
    override val area: Float,
    override val description: String,
) : Property