package com.renting.app.feature.search.results

import com.arkivanov.decompose.ComponentContext

internal class DefaultSearchResultsComponent(
    componentContext: ComponentContext,
) : SearchResultsComponent, ComponentContext by componentContext
