package com.renting.app.feature.filters

interface FiltersComponent {

    fun onFilterClick(id: String)

    fun onRangeFilterChange(id: String, value: IntRange)

    fun onTextFilterChange(id: String, value: String)
}
