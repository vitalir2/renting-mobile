package com.renting.app.feature.property.repository

import com.renting.app.feature.property.model.FamilyHouse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class NetworkProperty {
    abstract val id: Long
    abstract val owner: NetworkPropertyOwner
    abstract val area: Float
}

@Serializable
@SerialName("family_house")
internal class NetworkFamilyHouse(
    override val id: Long,
    override val owner: NetworkPropertyOwner,
    override val area: Float,
) : NetworkProperty()

internal fun NetworkFamilyHouse.toDomainModel(): FamilyHouse {
    return FamilyHouse(
        id = id,
        location = "todo_$id",
        owner = owner.toDomainModel(),
        area = area,
        description = "",
    )
}
