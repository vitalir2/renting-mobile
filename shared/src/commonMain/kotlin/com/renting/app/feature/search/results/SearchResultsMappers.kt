package com.renting.app.feature.search.results

internal object SearchResultsMappers {

    val stateToModel: SearchResultsStore.State.() -> SearchResultsComponent.Model = {
        SearchResultsComponent.Model(
            quickFilters = quickFilters,
            searchState = searchState,
        )
    }
}
