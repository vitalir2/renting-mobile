package com.renting.app.feature.home

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.repository.LoginRepository

internal class DefaultHomeGraph(
    authGraph: AuthGraph,
) : HomeGraph {

    override val loginRepository: LoginRepository = authGraph.loginRepository
}
