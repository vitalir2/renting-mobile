package com.renting.app.feature.registration

internal object RegistrationStoreMapper {

    val stateToModel: RegistrationStore.State.() -> RegistrationComponent.Model = {
        RegistrationComponent.Model(
            registrationForm = registrationForm,
        )
    }
}
