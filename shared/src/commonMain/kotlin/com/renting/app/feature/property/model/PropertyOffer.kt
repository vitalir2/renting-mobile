package com.renting.app.feature.property.model

data class PropertyOffer(
    val priceType: PriceType,
    val price: Int,
) {

    enum class PriceType {
        PER_NIGHT,
        PER_MONTH,
        SELL,
    }
}
