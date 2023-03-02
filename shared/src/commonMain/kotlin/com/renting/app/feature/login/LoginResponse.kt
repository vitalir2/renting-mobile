package com.renting.app.feature.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginResponse(
    @SerialName("token")
    val token: String,
)
