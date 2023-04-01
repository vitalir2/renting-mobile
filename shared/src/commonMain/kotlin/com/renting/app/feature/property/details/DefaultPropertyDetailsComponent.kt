package com.renting.app.feature.property.details

import com.arkivanov.decompose.ComponentContext

class DefaultPropertyDetailsComponent(
    componentContext: ComponentContext,
) : PropertyDetailsComponent, ComponentContext by componentContext
