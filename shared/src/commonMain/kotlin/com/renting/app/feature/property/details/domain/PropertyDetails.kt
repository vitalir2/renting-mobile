package com.renting.app.feature.property.details.domain

import com.renting.app.core.utils.LoremIpsum
import com.renting.app.feature.property.model.Apartment
import com.renting.app.feature.property.model.Building
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Land
import com.renting.app.feature.property.model.Property
import com.renting.app.feature.property.model.PropertyOffer
import com.renting.app.feature.property.model.PropertyOwner

data class PropertyDetails(
    val property: Property,
    val propertyOffer: PropertyOffer,
    val description: String,
)

internal object PropertyDetailsPreviews {
    val familyHouse: PropertyDetails
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
                images = emptyList(),
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
            ),
            description = LoremIpsum.DEFAULT,
        )

    val apartment: PropertyDetails
        get() = PropertyDetails(
            property = Apartment(
                id = 123L,
                location = "Grand City St. 100 New York, United States",
                owner = PropertyOwner(
                    id = 1234L,
                    firstName = "Natasya",
                    lastName = "Wilodra",
                    phoneNumber = "972-848-9754",
                ),
                area = 84f,
                images = emptyList(),
                building = Building(
                    fromYear = 2020,
                    material = "Brick",
                    type = "NEW_CONSTRUCTION",
                    numberOfFloors = 14,
                ),
                features = listOf(Apartment.Feature.ELEVATOR),
                numberOfRooms = 4,
                floor = 7,
                number = "153",
            ),
            propertyOffer = PropertyOffer(
                price = 80,
                priceType = PropertyOffer.PriceType.PER_NIGHT,
            ),
            description = LoremIpsum.DEFAULT,
        )

    val land: PropertyDetails
        get() = PropertyDetails(
            property = Land(
                id = 123L,
                location = "Grand City St. 100 New York, United States",
                owner = PropertyOwner(
                    id = 1234L,
                    firstName = "Natasya",
                    lastName = "Wilodra",
                    phoneNumber = "972-848-9754",
                ),
                area = 84f,
                images = emptyList(),
            ),
            propertyOffer = PropertyOffer(
                price = 80,
                priceType = PropertyOffer.PriceType.PER_NIGHT,
            ),
            description = LoremIpsum.DEFAULT,
        )
}
