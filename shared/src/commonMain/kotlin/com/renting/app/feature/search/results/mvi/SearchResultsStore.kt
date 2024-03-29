package com.renting.app.feature.search.results.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.results.SearchState
import com.renting.app.feature.search.results.mvi.SearchResultsStore.Intent
import com.renting.app.feature.search.results.mvi.SearchResultsStore.State

internal interface SearchResultsStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        object ResetFilters : Intent
        data class SearchSnippets(val query: String) : Intent
        data class ApplyQuickFilter(val type: PropertyType) : Intent
    }

    data class State(
        val quickFilters: PropertyTypeQuickFilters? = null,
        val searchState: SearchState = SearchState.Loading,
    )
}
