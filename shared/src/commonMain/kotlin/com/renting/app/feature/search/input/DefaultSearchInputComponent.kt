package com.renting.app.feature.search.input

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.search.input.SearchInputComponent.Model

internal class DefaultSearchInputComponent(
    componentContext: ComponentContext,
    private val onFullFiltersClick: () -> Unit,
    private val onSearchClick: (query: String) -> Unit,
) : SearchInputComponent, ComponentContext by componentContext {

    private val innerModels = MutableValue(
        Model(
            query = "",
        )
    )
    override val models: Value<Model> = innerModels

    override fun onFullFiltersClicked() {
        onFullFiltersClick()
    }

    override fun onQueryChanged(content: String) {
        innerModels.value = innerModels.value.copy(
            query = content,
        )
    }

    override fun onSearchClicked() {
        onSearchClick(innerModels.value.query)
    }
}
