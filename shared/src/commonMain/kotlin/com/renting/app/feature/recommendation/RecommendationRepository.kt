package com.renting.app.feature.recommendation

import com.renting.app.feature.property.PropertySnippet

interface RecommendationRepository {

    suspend fun getRecommendations(): List<PropertySnippet>
}
