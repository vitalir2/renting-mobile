package com.renting.app.feature.search

import com.renting.app.core.logger.Logging
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.network.CommonErrorResponse
import com.renting.app.feature.property.PropertySnippet
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class DefaultSearchRepository(
    private val httpClient: HttpClient,
) : SearchRepository {

    override suspend fun search(query: String): Either<Exception, List<PropertySnippet>> {
        val response = httpClient.get("/api/announcements/open/search") {
            parameter("keywords", query)
            contentType(ContentType.Application.Json)
        }

        return if (response.status.isSuccess()) {
            val snippets = response.body<List<PropertySnippet>>()
            snippets.right()
        } else {
            val error = response.body<CommonErrorResponse>()
            logger.error { "Got response with error=${error.message}" }
            Exception(error.message).left()
        }
    }

    private companion object : Logging()
}
