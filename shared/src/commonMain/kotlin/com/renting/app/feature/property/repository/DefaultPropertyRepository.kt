package com.renting.app.feature.property.repository

import com.renting.app.core.auth.repository.external.NetworkUser
import com.renting.app.core.auth.repository.toDomainModel
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.network.CommonErrorResponse
import com.renting.app.feature.property.model.FamilyHouse
import com.renting.app.feature.property.model.Property
import com.renting.app.feature.property.model.PropertyId
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal class DefaultPropertyRepository(
    private val httpClient: HttpClient,
) : PropertyRepository {

    override suspend fun get(id: PropertyId): Either<Exception, Property> {
        val response = httpClient.get("/api/properties/housing/family-houses/$id") {
            contentType(ContentType.Application.Json)
        }
        return if (response.status.isSuccess()) {
            val property = response.body<NetworkFamilyHouse>()
            property.toDomainModel().right()
        } else {
            val error = response.body<CommonErrorResponse>()
            IllegalStateException(error.message).left()
        }
    }
}

private fun NetworkFamilyHouse.toDomainModel(): FamilyHouse {
    return FamilyHouse(
        id = id,
        location = "todo_$id",
        owner = owner.toDomainModel(),
        area = area,
        description = "",
    )
}

@Serializable
internal sealed class NetworkProperty {
    abstract val id: Long
    abstract val owner: NetworkUser
    abstract val area: Int
}

@Serializable
@SerialName("family_house")
internal class NetworkFamilyHouse(
    override val id: Long,
    override val owner: NetworkUser,
    override val area: Int,
) : NetworkProperty()
