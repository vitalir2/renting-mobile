package com.renting.app.feature.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.search.SearchInputComponent.Model

internal class DefaultSearchInputComponent(
    componentContext: ComponentContext,
    private val openFullFilters: () -> Unit,
    private val openSearchResults: (query: String) -> Unit,
) : SearchInputComponent, ComponentContext by componentContext {

    private val innerModels = MutableValue(
        Model(
            query = "",
        )
    )
    override val models: Value<Model> = innerModels

    override fun onFullFiltersClicked() {
        openFullFilters()
    }

    override fun onQueryChanged(content: String) {
        innerModels.value = innerModels.value.copy(
            query = content,
        )
    }

    override fun onSearchClicked() {
        openSearchResults(innerModels.value.query)
    }
}
