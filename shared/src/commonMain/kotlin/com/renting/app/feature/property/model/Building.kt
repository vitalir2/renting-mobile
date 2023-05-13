package com.renting.app.feature.property.model

data class Building(
    val fromYear: Int,
    val material: String,
    val type: Type,
    val numberOfFloors: Int,
) {

    enum class Type {
        NEW_CONSTRUCTION,
        SECONDARY,
        UNKNOWN,
    }
}
