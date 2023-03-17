package com.renting.app.feature.registration

import com.arkivanov.decompose.value.Value
import com.renting.app.core.validation.TextField

interface RegistrationComponent {

    val models: Value<Model>

    fun completeRegistration()

    data class Model(
        val login: TextField,
        val password: TextField,
        val email: TextField,
        val firstName: TextField,
        val lastName: TextField,
        val patronymic: TextField?,
    )
}
