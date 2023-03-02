package com.renting.app.feature.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("type")
    val type: String,
    @SerialName("status")
    val status: Int,
    @SerialName("instance")
    val instance: String,
)
