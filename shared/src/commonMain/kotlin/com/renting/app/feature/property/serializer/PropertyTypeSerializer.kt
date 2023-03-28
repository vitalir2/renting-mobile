package com.renting.app.feature.property.serializer

import com.renting.app.feature.property.PropertyType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PropertyTypeSerializer : KSerializer<PropertyType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("PropertyType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: PropertyType) {
        encoder.encodeString(value.serialName)
    }

    override fun deserialize(decoder: Decoder): PropertyType {
        val serialName = decoder.decodeString()
        return serialName.toPropertyType()
    }

    private val PropertyType.serialName: String
        get() = when (this) {
            PropertyType.FAMILY_HOUSE -> PropertyTypeName.FAMILY_HOUSE
            PropertyType.LAND -> PropertyTypeName.LAND
            PropertyType.APARTMENT -> PropertyTypeName.APARTMENT
        }

    private fun String.toPropertyType(): PropertyType {
        return when (this) {
            PropertyTypeName.FAMILY_HOUSE -> PropertyType.FAMILY_HOUSE
            PropertyTypeName.LAND -> PropertyType.LAND
            PropertyTypeName.APARTMENT -> PropertyType.APARTMENT
            else -> error("Unknown property type=$this")
        }
    }

    private object PropertyTypeName {
        const val FAMILY_HOUSE = "FAMILY_HOUSE"
        const val LAND = "LAND"
        const val APARTMENT = "APARTMENT"
    }
}
