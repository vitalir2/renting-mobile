package com.renting.app.feature.registration.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField
import com.renting.app.feature.registration.mvi.RegistrationStore.Intent
import com.renting.app.feature.registration.mvi.RegistrationStore.Label
import com.renting.app.feature.registration.mvi.RegistrationStore.State

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
