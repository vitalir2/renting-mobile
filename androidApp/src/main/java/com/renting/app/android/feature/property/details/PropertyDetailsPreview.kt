package com.renting.app.android.feature.property.details

import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.PropertyDetails
import com.renting.app.feature.property.model.PropertyOffer
import com.renting.app.feature.property.model.PropertyOwner

val PropertyDetails.Companion.preview: PropertyDetails
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
            description = """
                             Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                             sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                             Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
                             ut aliquip ex ea commodo consequat. 
                             Duis aute irure dolor in reprehenderit in voluptate 
                             velit esse cillum dolore eu fugiat nulla pariatur. 
                             Excepteur sint occaecat cupidatat non proident, 
                             sunt in culpa qui officia 
                             deserunt mollit anim id est laborum.
                        """.trimIndent(),
        ),
        propertyOffer = PropertyOffer(
            price = 80,
            priceType = PropertyOffer.PriceType.PER_NIGHT,
        )
    )
