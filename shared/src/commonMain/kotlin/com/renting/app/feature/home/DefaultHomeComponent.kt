package com.renting.app.feature.home

import com.arkivanov.decompose.ComponentContext

internal class DefaultHomeComponent(
    componentContext: ComponentContext,
) : HomeComponent, ComponentContext by componentContext
