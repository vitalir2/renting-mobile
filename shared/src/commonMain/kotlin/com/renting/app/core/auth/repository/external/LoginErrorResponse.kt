package com.renting.app.core.auth.repository.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class LoginErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("type")
    val type: String,
    @SerialName("status")
    val status: Int,
    @SerialName("instance")
    val instance: String,
)
