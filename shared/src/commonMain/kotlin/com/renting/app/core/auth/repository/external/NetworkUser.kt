package com.renting.app.core.auth.repository.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class NetworkUser(
    @SerialName("username")
    val login: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("imagePath")
    val imagePathWithoutHost: String?,
)
