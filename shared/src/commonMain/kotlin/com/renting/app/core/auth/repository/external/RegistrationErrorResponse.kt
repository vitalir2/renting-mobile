package com.renting.app.core.auth.repository.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class RegistrationErrorResponse(
    @SerialName("fieldErrorMessages")
    val errors: RegistrationErrors,
)

@Serializable
internal class RegistrationErrors(
    @SerialName("username")
    val login: String? = null,
    @SerialName("password")
    val password: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("phoneNumber")
    val phoneNumber: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
)
