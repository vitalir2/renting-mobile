package com.renting.app.feature.property.details.ui.model

import com.renting.app.core.model.Image
import com.renting.app.core.utils.LoremIpsum
import com.renting.app.feature.property.details.domain.PropertyDetails
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails.MainInfo
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails.OwnerInfo
import com.renting.app.feature.property.model.Apartment
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Land
import com.renting.app.feature.property.model.PropertyOffer
import com.renting.app.feature.property.model.PropertyOwner
import kotlin.math.roundToInt

data class ComponentPropertyDetails(
    val images: List<Image>,
    val mainInfo: MainInfo,
    val ownerInfo: OwnerInfo,
    val location: String,
    val description: String,
) {

    sealed interface MainInfo {
        val price: String
        val area: String

        data class Apartment(
            override val price: String,
            override val area: String,
            val numberOfRooms: String,
            val floor: String,
        ) : MainInfo

        data class FamilyHouse(
            override val price: String,
            override val area: String,
            val numberOfRooms: String,
            val renovationType: String,
        ) : MainInfo

        data class Land(
            override val price: String,
            override val area: String,
        ) : MainInfo
    }

    data class OwnerInfo(
        val avatar: Image?,
        val fullName: String,
        val phoneNumber: String,
    )
}

internal fun PropertyDetails.toUiModel(): ComponentPropertyDetails {
    val price = propertyOffer.formattedPrice
    val area = "${property.area.roundToInt()} ㎡"

    return ComponentPropertyDetails(
        images = property.images,
        mainInfo = when (property) {
            is Apartment -> MainInfo.Apartment(
                price = price,
                area = area,
                numberOfRooms = formatNumberOfRooms(property.numberOfRooms),
                floor = "${property.floor} of ${property.building.numberOfFloors}",
            )
            is FamilyHouse -> MainInfo.FamilyHouse(
                price = price,
                area = area,
                numberOfRooms = formatNumberOfRooms(property.numberOfRooms),
                renovationType = property.renovationType,
            )
            is Land -> MainInfo.Land(
                price = price,
                area = area,
            )
        },
        ownerInfo = property.owner.toUiModel(),
        location = property.location,
        description = description,
    )
}

private fun formatNumberOfRooms(numberOfRooms: Int): String =
    when (numberOfRooms) {
        1 -> "1 room"
        else -> "$numberOfRooms rooms"
    }

internal fun PropertyOwner.toUiModel() =
    OwnerInfo(
        avatar = null,
        fullName = "$firstName $lastName",
        phoneNumber = phoneNumber,
    )

private val PropertyOffer.formattedPrice: String
    get() {
        val price = "$price ₽"
        val type = when (priceType) {
            PropertyOffer.PriceType.PER_NIGHT -> " / night"
            PropertyOffer.PriceType.PER_MONTH -> " / month"
            PropertyOffer.PriceType.SELL -> " "
        }
        return "$price$type"
    }
