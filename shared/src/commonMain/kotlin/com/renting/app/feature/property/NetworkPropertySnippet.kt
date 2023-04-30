package com.renting.app.feature.property

import com.renting.app.core.model.Image
import com.renting.app.feature.property.serializer.PropertyTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
internal class NetworkPropertySnippet(
    @SerialName("id")
    val id: Long,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("imagePathList")
    val imagePathList: List<String>,
    @SerialName("propertyType")
    @Serializable(with = PropertyTypeSerializer::class)
    val propertyType: PropertyType,
    @SerialName("property")
    val property: NetworkIdOnlyProperty,
)

@Serializable
internal class NetworkIdOnlyProperty(
    @SerialName("id")
    val id: Long,
)

internal fun NetworkPropertySnippet.toDomainModel(): PropertySnippet {
    return PropertySnippet(
        id = id,
        type = propertyType,
        location = description,
        image = imagePathList.firstOrNull()?.let(Image::Url),
        price = price.roundToInt(),
        propertyId = property.id,
    )
}
