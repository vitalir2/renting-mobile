package com.renting.app.feature.recommendation

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.network.CommonErrorResponse
import com.renting.app.feature.property.NetworkPropertySnippet
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.toDomainModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultRecommendationRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
): RecommendationRepository {

    override suspend fun getRecommendations(): Either<Exception, List<PropertySnippet>> = withContext(ioDispatcher) {
        val result = httpClient.get("/api/announcements/open") {
            contentType(ContentType.Application.Json)
        }
        if (result.status.isSuccess()) {
            val recommendationsResponse = result.body<List<NetworkPropertySnippet>>()
            recommendationsResponse
                .take(RECOMMENDATION_SNIPPETS_COUNT)
                .map(NetworkPropertySnippet::toDomainModel).right()
        } else {
            val errorBody = result.body<CommonErrorResponse>()
            DefaultLogger.log(errorBody.message)
            Exception(errorBody.message).left()
        }
    }

    companion object {
        private const val RECOMMENDATION_SNIPPETS_COUNT = 20
    }
}
