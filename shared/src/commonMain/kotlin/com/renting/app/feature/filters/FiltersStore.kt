package com.renting.app.feature.filters

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.feature.filters.FiltersStore.Intent
import com.renting.app.feature.filters.FiltersStore.State
import com.renting.app.feature.property.PropertyType

interface FiltersStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        data class SelectToggle(
            val id: String,
            val name: String,
        ) : Intent
        data class ChangeRangeFilter(
            val id: String,
            val range: IntRange,
        ) : Intent

        data class SelectFilterValue(
            val id: String,
            val value: String,
        ) : Intent
    }

    data class State(
        val filterGroups: List<PropertyFilterGroup> = createFilters(),
    )
}

private fun createFilters(): List<PropertyFilterGroup> = buildList {
    val category = PropertyFilterGroup(
        name = "Category",
        filter = PropertyTypeFilter(
            id = "PROPERTY_TYPE",
            toggles = listOf(
                SelectionPropertyFilter.Toggle(name = "House", value = PropertyType.FAMILY_HOUSE),
                SelectionPropertyFilter.Toggle(name = "Apartment", value = PropertyType.APARTMENT),
                SelectionPropertyFilter.Toggle(name = "Land", value = PropertyType.LAND),
            ),
        )
    )
    add(category)

    val price = PropertyFilterGroup(
        name = "Price Range",
        filter = PricePropertyFilter(
            id = "PRICE"
        ),
    )
    add(price)

    val location = PropertyFilterGroup(
        name = "Location",
        filter = PropertyLocationChooser(
            id = "PROPERTY_LOCATION",
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
