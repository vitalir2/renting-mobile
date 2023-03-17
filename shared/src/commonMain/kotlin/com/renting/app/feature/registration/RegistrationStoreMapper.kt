package com.renting.app.feature.registration

internal object RegistrationStoreMapper {

    val stateToModel: RegistrationStore.State.() -> RegistrationComponent.Model = {
        RegistrationComponent.Model(
            login = login,
            password = password,
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            email = email,
        )
    }
}
