package com.renting.app.feature.property.details.ui.model

import com.renting.app.feature.property.details.domain.PropertyDetails
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails.MainInfo
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails.OwnerInfo
import com.renting.app.feature.property.model.PropertyOffer
import com.renting.app.feature.property.model.PropertyOwner
import kotlin.math.roundToInt

data class ComponentPropertyDetails(
    val mainInfo: MainInfo,
    val ownerInfo: OwnerInfo,
    val location: String,
) {

    data class MainInfo(
        val price: String,
        val area: String,
    )

    data class OwnerInfo(
        val fullName: String,
        val phoneNumber: String,
    )

    companion object {
        val preview: ComponentPropertyDetails
            get() = PropertyDetails.preview.toUiModel()
    }
}

internal fun PropertyDetails.toUiModel() = ComponentPropertyDetails(
    mainInfo = MainInfo(
        price = propertyOffer.formattedPrice,
        area = "${property.area.roundToInt()} ㎡",
    ),
    ownerInfo = property.owner.toUiModel(),
    location = property.location,
)

internal fun PropertyOwner.toUiModel() =
    OwnerInfo(
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
