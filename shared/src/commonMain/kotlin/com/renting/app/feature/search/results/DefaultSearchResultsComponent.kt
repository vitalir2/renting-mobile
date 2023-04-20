package com.renting.app.feature.search.results

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.search.DefaultSearchInputComponent
import com.renting.app.feature.search.SearchInputComponent

internal class DefaultSearchResultsComponent(
    componentContext: ComponentContext,
    openFullFilters: () -> Unit,
    private val openPropertyDetails: (id: Long) -> Unit,
    private val navigateBack: () -> Unit,

) : SearchResultsComponent, ComponentContext by componentContext {

    override val searchInputComponent: SearchInputComponent = DefaultSearchInputComponent(
        componentContext = childContext("search_input"),
        onFullFiltersClick = openFullFilters,
        onSearchClick = {
            // TODO request search
        },
    )

    override fun onResetQuickFiltersSelected() {
        // Handle intent
    }

    override fun onQuickFilterToggled(quickFilter: PropertyTypeQuickFilter) {
        // Handle intent
    }

    override fun onNavigateBackRequested() {
        navigateBack()
    }

    override fun onSnippetClicked(id: Long) {
        openPropertyDetails(id)
    }
}
