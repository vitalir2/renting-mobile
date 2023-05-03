package com.renting.app.feature.property.details.domain

import com.renting.app.feature.property.model.Building
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Property
import com.renting.app.feature.property.model.PropertyOffer
import com.renting.app.feature.property.model.PropertyOwner

data class PropertyDetails(
    val property: Property,
    val propertyOffer: PropertyOffer,
) {
    companion object {
        val preview: PropertyDetails
            get() = PropertyDetails(
                property = FamilyHouse(
                    id = 123L,
                    location = "Grand City St. 100 New York, United States",
                    owner = PropertyOwner(
                        id = 1234L,
                        firstName = "Natasya",
                        lastName = "Wilodra",
                        phoneNumber = "972-848-9754",
                    ),
                    area = 84f,
                    building = Building(
                        fromYear = 2020,
                        material = "Brick",
                        type = "NEW_CONSTRUCTION",
                        numberOfFloors = 2,
                    ),
                    features = listOf(FamilyHouse.Feature.SWIMMING_POOL),
                    numberOfRooms = 4,
                    renovationType = "No renovation",
                ),
                propertyOffer = PropertyOffer(
                    price = 80,
                    priceType = PropertyOffer.PriceType.PER_NIGHT,
                )
            )
    }
}
