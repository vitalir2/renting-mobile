package com.renting.app.feature.search.results.component

import com.arkivanov.decompose.value.Value
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.input.SearchInputComponent
import com.renting.app.feature.search.results.SearchState

interface SearchResultsComponent {

    val models: Value<Model>

    val searchInputComponent: SearchInputComponent

    fun onResetQuickFiltersSelected()

    fun onQuickFilterToggled(type: PropertyType)

    fun onNavigateBackRequested()

    fun onSnippetClicked(id: Long)

    data class Model(
        val quickFilters: PropertyTypeQuickFilters?,
        val searchState: SearchState,
    )
}
