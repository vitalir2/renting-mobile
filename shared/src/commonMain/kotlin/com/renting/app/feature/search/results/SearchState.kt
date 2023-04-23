package com.renting.app.feature.search.results

import com.renting.app.feature.property.PropertySnippet

sealed interface SearchState {
    object Loading : SearchState
    object EmptyResults : SearchState
    object Error : SearchState
    data class Results(val snippets: List<PropertySnippet>) : SearchState
}
