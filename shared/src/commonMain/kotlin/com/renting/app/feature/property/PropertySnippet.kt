package com.renting.app.feature.property

import com.renting.app.core.model.Image
import com.renting.app.feature.property.model.PropertyId

data class PropertySnippet(
    val id: Long,
    val type: PropertyType,
    val location: String,
    val image: Image?,
    val price: Int,
    val propertyId: PropertyId,
) {
    companion object {
        var previewId = 0L
    }
}
