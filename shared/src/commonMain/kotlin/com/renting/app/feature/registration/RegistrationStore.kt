package com.renting.app.feature.registration

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.core.validation.FieldForm
import com.renting.app.core.validation.TextField
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import com.renting.app.feature.registration.RegistrationStore.State

internal interface RegistrationStore : Store<Intent, State, Label> {

    sealed interface Intent {
        object CompleteRegistration : Intent
        data class SetFieldValue(val id: TextField.Id, val value: String) : Intent
    }

    sealed interface Label {
        object RegistrationCompleted : Label
    }

    data class State(
        val registrationForm: FieldForm,
    )
}
