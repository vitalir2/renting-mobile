package com.renting.app.feature.search

import com.renting.app.core.monad.Either
import com.renting.app.feature.property.PropertySnippet

interface SearchRepository {

    suspend fun search(query: String): Either<Exception, List<PropertySnippet>>
}
