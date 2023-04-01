package com.renting.app.feature.property

import com.renting.app.core.model.Image

data class PropertySnippet(
    val id: Long,
    val type: PropertyType,
    val location: String,
    val image: Image?,
    val price: Int,
)
