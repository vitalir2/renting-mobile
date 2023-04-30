package com.renting.app.android.feature.property

import com.renting.app.core.model.Image
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType

val PropertySnippet.Companion.preview: PropertySnippet
    get() = PropertySnippet(
        id = previewId++,
        type = PropertyType.FAMILY_HOUSE,
        location = "2857 E Detroit St, Chandler, AZ 85225",
        image = Image.Url(
            "https://photos.zillowstatic.com/fp/e57899af93feb02883e71c4a155c859f-p_e.jpg"
        ),
        price = 2350,
        propertyId = previewId + 1,
    )
