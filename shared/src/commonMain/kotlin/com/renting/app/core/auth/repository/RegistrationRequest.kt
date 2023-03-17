package com.renting.app.core.auth.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class RegistrationRequest(
    @SerialName("username")
    val login: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
)
