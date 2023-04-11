package com.renting.app.feature.property.details

import com.arkivanov.decompose.ComponentContext

internal class DefaultPropertyDetailsComponent(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit,
) : PropertyDetailsComponent, ComponentContext by componentContext {

    override fun onBackButtonClick() {
        navigateBack()
    }
}
