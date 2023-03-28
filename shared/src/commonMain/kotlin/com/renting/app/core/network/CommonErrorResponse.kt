package com.renting.app.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CommonErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("type")
    val type: String,
    @SerialName("status")
    val status: Int,
)
