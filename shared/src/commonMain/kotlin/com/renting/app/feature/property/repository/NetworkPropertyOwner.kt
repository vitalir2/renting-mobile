package com.renting.app.feature.property.repository

import com.renting.app.feature.property.model.PropertyOwner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class NetworkPropertyOwner(
    @SerialName("id")
    val id: Long,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
)

internal fun NetworkPropertyOwner.toDomainModel(): PropertyOwner {
    return PropertyOwner(
        id = id,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
    )
}
