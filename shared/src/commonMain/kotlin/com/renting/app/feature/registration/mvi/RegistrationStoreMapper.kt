package com.renting.app.feature.registration.mvi

import com.renting.app.feature.registration.component.RegistrationComponent

internal object RegistrationStoreMapper {

    val stateToModel: RegistrationStore.State.() -> RegistrationComponent.Model = {
        RegistrationComponent.Model(
            registrationForm = registrationForm,
        )
    }
}
