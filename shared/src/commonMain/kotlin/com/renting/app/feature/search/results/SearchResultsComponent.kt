package com.renting.app.feature.search.results

import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.search.SearchInputComponent

interface SearchResultsComponent {

    val searchInputComponent: SearchInputComponent

    fun onResetQuickFiltersSelected()

    fun onQuickFilterToggled(quickFilter: PropertyTypeQuickFilter)

    fun onNavigateBackRequested()

    fun onSnippetClicked(id: Long)
}
