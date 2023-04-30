package com.renting.app.feature.property.repository

import com.renting.app.feature.property.model.Apartment
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Land
import com.renting.app.feature.property.model.Property
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

@Serializable
@SerialName("land")
internal class NetworkLand(
    override val id: Long,
    override val owner: NetworkPropertyOwner,
    override val area: Float,
) : NetworkProperty()

@Serializable
@SerialName("apartment")
internal class NetworkApartment(
    override val id: Long,
    override val owner: NetworkPropertyOwner,
    override val area: Float,
) : NetworkProperty()

internal fun NetworkProperty.toDomainModel(): Property {
    return when (this) {
        is NetworkApartment -> toDomainModel()
        is NetworkFamilyHouse -> toDomainModel()
        is NetworkLand -> toDomainModel()
    }
}

internal fun NetworkFamilyHouse.toDomainModel(): FamilyHouse {
    return FamilyHouse(
        id = id,
        location = "todo_$id",
        owner = owner.toDomainModel(),
        area = area,
        description = "",
    )
}

internal fun NetworkLand.toDomainModel(): Land {
    return Land(
        id = id,
        location = "todo_$id",
        owner = owner.toDomainModel(),
        area = area,
        description = "",
    )
}

internal fun NetworkApartment.toDomainModel(): Apartment {
    return Apartment(
        id = id,
        location = "todo_$id",
        owner = owner.toDomainModel(),
        area = area,
        description = "",
    )
}
