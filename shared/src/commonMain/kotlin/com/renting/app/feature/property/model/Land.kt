package com.renting.app.feature.property.model

import com.renting.app.core.model.Image

data class Land(
    override val id: PropertyId,
    override val location: String,
    override val owner: PropertyOwner,
    override val area: Float,
    override val images: List<Image>,
) : Property
