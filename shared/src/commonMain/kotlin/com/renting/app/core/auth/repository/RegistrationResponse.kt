package com.renting.app.core.auth.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class RegistrationResponse(
    @SerialName("token")
    val token: String,
)
