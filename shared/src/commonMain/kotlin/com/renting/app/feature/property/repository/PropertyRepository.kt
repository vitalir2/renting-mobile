package com.renting.app.feature.property.repository

import com.renting.app.core.monad.Either
import com.renting.app.feature.property.model.OfferId
import com.renting.app.feature.property.model.PropertyDetails

interface PropertyRepository {

    suspend fun getDetails(id: OfferId): Either<Exception, PropertyDetails>
}
