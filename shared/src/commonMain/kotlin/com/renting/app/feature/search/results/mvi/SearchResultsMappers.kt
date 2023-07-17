package com.renting.app.feature.search.results.mvi

import com.renting.app.feature.search.results.SearchState
import com.renting.app.feature.search.results.component.SearchResultsComponent

internal object SearchResultsMappers {

    val stateToModel: SearchResultsStore.State.() -> SearchResultsComponent.Model = {
        val appliedFilter = quickFilters?.appliedFilter
        SearchResultsComponent.Model(
            quickFilters = quickFilters,
            searchState = if (searchState is SearchState.Results && appliedFilter != null) {
                val filteredSnippets = appliedFilter.applyTo(searchState.snippets)
                if (filteredSnippets.isEmpty()) {
                    SearchState.EmptyResults
                } else {
                    searchState.copy(snippets = filteredSnippets)
                }
            } else {
                searchState
            },
        )
    }
}
