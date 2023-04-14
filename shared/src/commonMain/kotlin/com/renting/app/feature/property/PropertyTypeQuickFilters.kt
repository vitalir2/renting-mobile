package com.renting.app.feature.property

data class PropertyTypeQuickFilters(
    val list: List<PropertyTypeQuickFilter>,
) {

    val appliedFilter: PropertyTypeQuickFilter?
        get() = list.firstOrNull(PropertyTypeQuickFilter::isApplied)

    fun clearSelection(): PropertyTypeQuickFilters {
        return copy(
            list = list.map { it.copy(isApplied = false) }
        )
    }

    fun setActive(type: PropertyType): PropertyTypeQuickFilters {
        return copy(
            list = list.map { filter ->
                val isApplied = filter.type == type
                filter.copy(isApplied = isApplied)
            },
        )
    }

    companion object {
        val filtersOrder: List<PropertyType> = listOf(
            PropertyType.FAMILY_HOUSE,
            PropertyType.APARTMENT,
            PropertyType.LAND,
        )
    }
}
