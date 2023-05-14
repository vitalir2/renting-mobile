package com.renting.app.feature.filters

import com.arkivanov.decompose.ComponentContext

internal class DefaultFiltersComponent(
    componentContext: ComponentContext,
) : FiltersComponent, ComponentContext by componentContext {

    override fun onFilterClick(id: String) {
        // TODO add store
    }

    override fun onRangeFilterChange(id: String, value: IntRange) {
        // TODO add store
    }

    override fun onTextFilterChange(id: String, value: String) {
        // TODO add store
    }
}
