package com.renting.app.feature.filters

import com.arkivanov.decompose.ComponentContext

internal class DefaultFiltersComponent(
    componentContext: ComponentContext,
) : FiltersComponent, ComponentContext by componentContext
