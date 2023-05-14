package com.renting.app.feature.filters

import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType

interface FiltersStore {

    data class State(
        val filterGroups: List<PropertyFilterGroup> = createFilters(),
    )
}

private fun createFilters(): List<PropertyFilterGroup> = buildList {
    val category = PropertyFilterGroup(
        name = "Category",
        filter = object : ToggleablePropertyFilter<PropertyType>(
            toggles = listOf(
                Toggle(name = "House", value = PropertyType.FAMILY_HOUSE),
                Toggle(name = "Apartment", value = PropertyType.APARTMENT),
                Toggle(name = "Land", value = PropertyType.LAND),
            ),
        ) {
            override val predicate: (
                PropertyType,
                snippet: PropertySnippet,
            ) -> Boolean = { type, snippet -> type == snippet.type }
        }
    )
    add(category)

    val price = PropertyFilterGroup(
        name = "Price Range",
        filter = PricePropertyFilter(),
    )
    add(price)

    val location = PropertyFilterGroup(
        name = "Location",
        filter = PropertyLocationSelector(
            values = listOf(
                "Moscow",
                "Saint Petersburg",
                "Yekaterinburg",
                "Novosibirsk",
                "Kazan",
            ),
            selectedValue = "Moscow",
        ),
    )
    add(location)
}
