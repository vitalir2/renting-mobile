package com.renting.app.feature.search.results

import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.search.DummySearchInputComponent
import com.renting.app.feature.search.SearchInputComponent

class DummySearchResultsComponent : SearchResultsComponent {

    override val searchInputComponent: SearchInputComponent = DummySearchInputComponent()

    override fun onResetQuickFiltersSelected() {
        // Nothing to do
    }

    override fun onQuickFilterToggled(quickFilter: PropertyTypeQuickFilter) {
        // Nothing to do
    }

    override fun onNavigateBackRequested() {
        // Nothing to do
    }

    override fun onSnippetClicked(id: Long) {
        // Nothing to do
    }
}
