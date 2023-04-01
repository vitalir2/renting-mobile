package com.renting.app.feature.home

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.UserRepository
import com.renting.app.feature.recommendation.RecommendationGraph
import com.renting.app.feature.recommendation.RecommendationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal class DefaultHomeGraph(
    authGraph: AuthGraph,
    recommendationGraph: RecommendationGraph,
) : HomeGraph {

    override val coroutineScope: CoroutineScope = MainScope()

    override val authRepository: AuthRepository = authGraph.authRepository

    override val userRepository: UserRepository = authGraph.userRepository

    override val recommendationRepository: RecommendationRepository = recommendationGraph.recommendationRepository
}
