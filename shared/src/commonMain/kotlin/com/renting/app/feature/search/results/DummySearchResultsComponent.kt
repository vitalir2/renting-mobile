package com.renting.app.feature.search.results

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.search.DummySearchInputComponent
import com.renting.app.feature.search.SearchInputComponent

class DummySearchResultsComponent(
    initModel: SearchResultsComponent.Model? = null
) : SearchResultsComponent {

    override val models: Value<SearchResultsComponent.Model> = MutableValue(
        initModel ?: SearchResultsComponent.Model(
            searchState = SearchState.Loading,
            quickFilters = null,
        )
    )

    override val searchInputComponent: SearchInputComponent = DummySearchInputComponent()

    override fun onResetQuickFiltersSelected() {
        // Nothing to do
    }

    override fun onQuickFilterToggled(type: PropertyType) {
        // Nothing to do
    }

    override fun onNavigateBackRequested() {
        // Nothing to do
    }

    override fun onSnippetClicked(id: Long) {
        // Nothing to do
    }
}
