package com.renting.app.feature.search.results

import com.arkivanov.decompose.value.Value
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.SearchInputComponent

interface SearchResultsComponent {

    val models: Value<Model>

    val searchInputComponent: SearchInputComponent

    fun onResetQuickFiltersSelected()

    fun onQuickFilterToggled(quickFilter: PropertyTypeQuickFilter)

    fun onNavigateBackRequested()

    fun onSnippetClicked(id: Long)

    data class Model(
        val quickFilters: PropertyTypeQuickFilters?,
        val searchState: SearchState,
    )
}
