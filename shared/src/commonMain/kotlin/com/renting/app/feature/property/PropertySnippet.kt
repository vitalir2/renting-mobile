package com.renting.app.feature.property

data class PropertySnippet(
    val id: Long,
    val type: PropertyType,
    val location: String,
    val image: String?,
    val price: Int,
)
