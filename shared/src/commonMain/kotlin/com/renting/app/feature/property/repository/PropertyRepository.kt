package com.renting.app.feature.property.repository

import com.renting.app.core.monad.Either
import com.renting.app.feature.property.model.PropertyDetails
import com.renting.app.feature.property.model.PropertyId

interface PropertyRepository {

    suspend fun get(id: PropertyId): Either<Exception, PropertyDetails>
}
