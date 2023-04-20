package com.renting.app.feature.search.results

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.results.SearchResultsStore.Intent
import com.renting.app.feature.search.results.SearchResultsStore.State

internal interface SearchResultsStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        object ResetFilters : Intent
        data class SearchSnippets(val query: String) : Intent
        data class ApplyFilter(val quickFilter: PropertyTypeQuickFilter) : Intent
    }

    data class State(
        val query: String,
        val quickFilters: PropertyTypeQuickFilters? = null,
        val searchState: SearchState = SearchState.Loading,
    )

    sealed interface SearchState {
        object Loading : SearchState
        object EmptyResults : SearchState
        data class Results(val snippets: List<PropertySnippet>) : SearchState
    }
}
