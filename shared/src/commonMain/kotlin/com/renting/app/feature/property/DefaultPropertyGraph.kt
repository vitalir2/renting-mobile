package com.renting.app.feature.property

import com.renting.app.feature.property.repository.DefaultPropertyRepository
import com.renting.app.feature.property.repository.PropertyRepository
import io.ktor.client.*

internal class DefaultPropertyGraph(
    httpClient: HttpClient,
) : PropertyGraph {

    override val propertyRepository: PropertyRepository = DefaultPropertyRepository(
        httpClient = httpClient,
    )
}
