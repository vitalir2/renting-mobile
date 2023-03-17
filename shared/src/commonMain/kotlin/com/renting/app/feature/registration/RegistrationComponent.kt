package com.renting.app.feature.registration

import com.arkivanov.decompose.value.Value
import com.renting.app.core.validation.TextField

interface RegistrationComponent {

    val models: Value<Model>

    fun onFieldChanged(id: TextField.Id, value: String)

    fun completeRegistration()

    data class Model(
        val registrationForm: List<TextField>,
    )
}
