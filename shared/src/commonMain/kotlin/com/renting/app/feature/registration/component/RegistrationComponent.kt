package com.renting.app.feature.registration.component

import com.arkivanov.decompose.value.Value
import com.renting.app.core.form.FieldForm
import com.renting.app.core.form.TextField

interface RegistrationComponent {

    val models: Value<Model>

    fun onFieldChanged(id: TextField.Id, value: String)

    fun onLoginRequired()

    fun completeRegistration()

    data class Model(
        val registrationForm: FieldForm,
    )
}
