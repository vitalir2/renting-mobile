package com.renting.app.feature.root.di

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.feature.home.HomeGraph
import com.renting.app.feature.login.di.LoginGraph
import com.renting.app.feature.property.PropertyGraph
import com.renting.app.feature.recommendation.RecommendationGraph
import com.renting.app.feature.registration.di.RegistrationGraph
import com.renting.app.feature.search.di.SearchGraph

interface RootGraph {

    val authGraph: AuthGraph

    val loginGraph: LoginGraph

    val registrationGraph: RegistrationGraph

    val homeGraph: HomeGraph

    val recommendationGraph: RecommendationGraph

    val searchGraph: SearchGraph

    val propertyGraph: PropertyGraph
}
