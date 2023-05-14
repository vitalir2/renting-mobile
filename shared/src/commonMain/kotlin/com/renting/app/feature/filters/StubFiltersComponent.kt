package com.renting.app.feature.filters

class StubFiltersComponent : FiltersComponent {
    override fun onFilterClick(id: String) {
        // Nothing to do
    }

    override fun onRangeFilterChange(id: String, value: IntRange) {
        // Nothing to do
    }

    override fun onTextFilterChange(id: String, value: String) {
        // Nothing to do
    }
}
