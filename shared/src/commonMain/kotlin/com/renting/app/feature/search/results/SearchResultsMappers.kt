package com.renting.app.feature.search.results

internal object SearchResultsMappers {

    val stateToModel: SearchResultsStore.State.() -> SearchResultsComponent.Model = {
        val appliedFilter = quickFilters?.appliedFilter
        SearchResultsComponent.Model(
            quickFilters = quickFilters,
            searchState = if (searchState is SearchState.Results && appliedFilter != null) {
                val filteredSnippets = appliedFilter.applyTo(searchState.snippets)
                searchState.copy(snippets = filteredSnippets)
            } else {
                searchState
            },
        )
    }
}
