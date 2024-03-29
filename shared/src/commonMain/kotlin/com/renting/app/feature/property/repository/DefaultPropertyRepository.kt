package com.renting.app.feature.property.repository

import com.renting.app.core.model.Image
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.network.CommonErrorResponse
import com.renting.app.feature.property.NetworkPropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.model.OfferId
import com.renting.app.feature.property.details.domain.PropertyDetails
import com.renting.app.feature.property.model.PropertyOffer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.math.roundToInt

internal class DefaultPropertyRepository(
    private val httpClient: HttpClient,
) : PropertyRepository {

    override suspend fun getDetails(id: OfferId): Either<Exception, PropertyDetails> {
        val offerResponse = httpClient.get("/api/announcements/open/$id") {
            contentType(ContentType.Application.Json)
        }
        if (offerResponse.status.isSuccess().not()) {
            return IllegalStateException(
                offerResponse.body<CommonErrorResponse>().message
            ).left()
        }

        val offer = offerResponse.body<NetworkPropertySnippet>()
        val propertyType = offer.propertyType
        val propertyTypePathPart = when (propertyType) {
            PropertyType.FAMILY_HOUSE -> "housing/family-houses"
            PropertyType.LAND -> "lands"
            PropertyType.APARTMENT -> "housing/apartments"
        }
        val propertyResponse = httpClient.get(
            "/api/properties/${propertyTypePathPart}/${offer.property.id}"
        ) {
            contentType(ContentType.Application.Json)
        }
        return if (propertyResponse.status.isSuccess()) {
            val property = when (propertyType) {
                PropertyType.FAMILY_HOUSE -> propertyResponse.body<NetworkFamilyHouse>()
                PropertyType.LAND -> propertyResponse.body<NetworkLand>()
                PropertyType.APARTMENT -> propertyResponse.body<NetworkApartment>()
            }.toDomainModel(images = offer.imagePathList.map(Image::Url))
            PropertyDetails(
                property = property,
                propertyOffer = PropertyOffer(
                    price = offer.price.roundToInt(),
                    priceType = PropertyOffer.PriceType.PER_NIGHT,
                ),
                description = offer.description,
            ).right()
        } else {
            val error = propertyResponse.body<CommonErrorResponse>()
            IllegalStateException(error.message).left()
        }
    }
}
