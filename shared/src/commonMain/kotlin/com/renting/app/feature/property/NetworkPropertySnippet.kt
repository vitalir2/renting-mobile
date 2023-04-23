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
)

internal fun NetworkPropertySnippet.toDomainModel(): PropertySnippet {
    return PropertySnippet(
        id = id,
        type = propertyType,
        location = description,
        image = imagePathList.firstOrNull()?.let(Image::Url),
        price = price.roundToInt(),
    )
}
