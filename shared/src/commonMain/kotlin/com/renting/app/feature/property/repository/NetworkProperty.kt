package com.renting.app.feature.property.repository

import com.renting.app.core.model.Image
import com.renting.app.feature.property.model.Apartment
import com.renting.app.feature.property.model.Building
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Land
import com.renting.app.feature.property.model.Property
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class NetworkProperty {
    @SerialName("id")
    abstract val id: Long
    @SerialName("owner")
    abstract val owner: NetworkPropertyOwner
    @SerialName("area")
    abstract val area: Float
}

@Serializable
@SerialName("family_house")
internal class NetworkFamilyHouse(
    @SerialName("id")
    override val id: Long,
    @SerialName("owner")
    override val owner: NetworkPropertyOwner,
    @SerialName("area")
    override val area: Float,
    @SerialName("numberOfRooms")
    val numberOfRooms: Int,
    @SerialName("renovationType")
    val renovationType: String,
    @SerialName("house")
    val building: NetworkFamilyBuilding,
) : NetworkProperty()

@Serializable
internal class NetworkFamilyBuilding(
    @SerialName("oneLineAddress")
    val address: String,
    @SerialName("buildingYear")
    val buildingYear: Int,
    @SerialName("swimmingPool")
    val hasSwimmingPool: Boolean,
    @SerialName("houseMaterial")
    val material: String,
    @SerialName("housingType")
    val housingType: String,
    @SerialName("numberOfFloors")
    val numberOfFloors: Int,
)

@Serializable
@SerialName("land")
internal class NetworkLand(
    @SerialName("id")
    override val id: Long,
    @SerialName("owner")
    override val owner: NetworkPropertyOwner,
    @SerialName("area")
    override val area: Float,
    @SerialName("oneLineAddress")
    val address: String,
) : NetworkProperty()

@Serializable
@SerialName("apartment")
internal class NetworkApartment(
    @SerialName("id")
    override val id: Long,
    @SerialName("owner")
    override val owner: NetworkPropertyOwner,
    @SerialName("area")
    override val area: Float,
    @SerialName("apartmentNumber")
    val number: String,
    @SerialName("floor")
    val floor: Int,
    @SerialName("numberOfRooms")
    val numberOfRooms: Int,
    @SerialName("renovationType")
    val renovationType: String,
    @SerialName("house")
    val building: NetworkApartmentBuilding,
) : NetworkProperty()

@Serializable
internal class NetworkApartmentBuilding(
    @SerialName("oneLineAddress")
    val address: String,
    @SerialName("buildingYear")
    val buildingYear: Int,
    @SerialName("elevator")
    val hasElevator: Boolean,
    @SerialName("houseMaterial")
    val material: String,
    @SerialName("housingType")
    val housingType: String,
    @SerialName("numberOfFloors")
    val numberOfFloors: Int,
)

internal fun NetworkProperty.toDomainModel(
    images: List<Image>,
): Property {
    return when (this) {
        is NetworkApartment -> toDomainModel(images)
        is NetworkFamilyHouse -> toDomainModel(images)
        is NetworkLand -> toDomainModel(images)
    }
}

internal fun NetworkFamilyHouse.toDomainModel(
    images: List<Image>,
): FamilyHouse {
    return FamilyHouse(
        id = id,
        location = building.address,
        owner = owner.toDomainModel(),
        area = area,
        images = images,
        building = building.toDomainModel(),
        features = buildList {
            if (building.hasSwimmingPool) {
                add(FamilyHouse.Feature.SWIMMING_POOL)
            }
        },
        numberOfRooms = numberOfRooms,
        renovationType = renovationType,
    )
}

internal fun NetworkLand.toDomainModel(
    images: List<Image>,
): Land {
    return Land(
        id = id,
        location = address,
        owner = owner.toDomainModel(),
        area = area,
        images = images,
    )
}

internal fun NetworkApartment.toDomainModel(
    images: List<Image>,
): Apartment {
    return Apartment(
        id = id,
        location = building.address,
        owner = owner.toDomainModel(),
        area = area,
        images = images,
        building = building.toDomainModel(),
        features = buildList {
            if (building.hasElevator) {
                add(Apartment.Feature.ELEVATOR)
            }
        },
        numberOfRooms = numberOfRooms,
        floor = floor,
        number = number,
    )
}

private fun NetworkApartmentBuilding.toDomainModel(): Building {
    return Building(
        fromYear = buildingYear,
        material = material,
        type = housingType.toBuildingType(),
        numberOfFloors = numberOfFloors,
    )
}

private fun NetworkFamilyBuilding.toDomainModel(): Building {
    return Building(
        fromYear = buildingYear,
        material = material,
        type = housingType.toBuildingType(),
        numberOfFloors = numberOfFloors,
    )
}

private fun String.toBuildingType(): Building.Type {
    return when (this) {
        "NEW_CONSTRUCTION" -> Building.Type.NEW_CONSTRUCTION
        "SECONDARY" -> Building.Type.SECONDARY
        else -> Building.Type.UNKNOWN
    }
}
