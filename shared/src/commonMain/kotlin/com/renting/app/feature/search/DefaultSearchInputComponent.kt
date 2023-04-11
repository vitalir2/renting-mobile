package com.renting.app.feature.search

import com.arkivanov.decompose.ComponentContext

internal class DefaultSearchInputComponent(
    componentContext: ComponentContext,
    private val changeContent: (content: String) -> Unit,
    private val openFullFilters: () -> Unit,
    private val openSearchResults: () -> Unit,
) : SearchInputComponent, ComponentContext by componentContext {

    override fun onFullFiltersClicked() {
        openFullFilters()
    }

    override fun onContentChanged(content: String) {
        changeContent(content)
    }

    override fun onSearchClicked() {
        openSearchResults()
    }
}
