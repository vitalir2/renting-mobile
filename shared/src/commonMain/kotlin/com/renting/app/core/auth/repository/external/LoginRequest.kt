package com.renting.app.core.auth.repository.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class LoginRequest(
    @SerialName("username")
    val login: String,
    @SerialName("password")
    val password: String,
)
