package com.renting.app.feature.registration

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.core.validation.TextField
import com.renting.app.feature.registration.RegistrationStore.Intent
import com.renting.app.feature.registration.RegistrationStore.Label
import com.renting.app.feature.registration.RegistrationStore.State

internal interface RegistrationStore : Store<Intent, State, Label> {

    sealed interface Intent {
        object CompleteRegistration : Intent
        data class SetLogin(val login: String) : Intent
    }

    sealed interface Label {
        object RegistrationCompleted : Label
    }

    data class State(
        val login: TextField = TextField(),
        val password: TextField = TextField(),
        val email: TextField = TextField(),
        val firstName: TextField = TextField(),
        val lastName: TextField = TextField(),
        val patronymic: TextField? = null,
    )
}