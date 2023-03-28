package com.renting.app.feature.recommendation

import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

internal class DefaultRecommendationGraph(
    httpClient: HttpClient,
    ioDispatcher: CoroutineDispatcher,
) : RecommendationGraph {

    override val recommendationRepository: RecommendationRepository =
        DefaultRecommendationRepository(
            httpClient = httpClient,
            ioDispatcher = ioDispatcher,
        )
}
