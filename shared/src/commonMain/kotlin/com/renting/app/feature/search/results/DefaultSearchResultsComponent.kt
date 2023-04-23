package com.renting.app.feature.search.results

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.core.utils.stateAsValue
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.search.DefaultSearchInputComponent
import com.renting.app.feature.search.SearchInputComponent
import com.renting.app.feature.search.results.SearchResultsStore.Intent

internal class DefaultSearchResultsComponent(
    componentContext: ComponentContext,
    initQuery: String,
    storeFactory: StoreFactory,
    openFullFilters: () -> Unit,
    searchGraph: SearchGraph,
    private val openPropertyDetails: (id: Long) -> Unit,
    private val navigateBack: () -> Unit,
) : SearchResultsComponent, ComponentContext by componentContext, SearchGraph by searchGraph {

    private val store = instanceKeeper.getStore {
        SearchResultsStoreFactory(
            initQuery = initQuery,
            storeFactory = storeFactory,
            searchRepository = searchRepository,
        ).create()
    }
    override val models: Value<SearchResultsComponent.Model> = store.stateAsValue()
        .map(SearchResultsMappers.stateToModel)

    override val searchInputComponent: SearchInputComponent = DefaultSearchInputComponent(
        componentContext = childContext("search_input"),
        onFullFiltersClick = openFullFilters,
        onSearchClick = { query -> store.accept(Intent.SearchSnippets(query)) },
    )

    override fun onResetQuickFiltersSelected() {
        store.accept(Intent.ResetFilters)
    }

    override fun onQuickFilterToggled(type: PropertyType) {
        store.accept(Intent.ApplyQuickFilter(type))
    }

    override fun onNavigateBackRequested() {
        navigateBack()
    }

    override fun onSnippetClicked(id: Long) {
        openPropertyDetails(id)
    }
}
