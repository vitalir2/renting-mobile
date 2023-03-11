package com.renting.app.feature.registration

import com.arkivanov.decompose.ComponentContext

internal class DefaultRegistrationComponent(
    componentContext: ComponentContext,
) : RegistrationComponent, ComponentContext by componentContext
