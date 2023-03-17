package com.renting.app.feature.login.di

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal class DefaultLoginGraph(
    authGraph: AuthGraph,
) : LoginGraph {

    override val coroutineScope: CoroutineScope = MainScope()

    override val authRepository: AuthRepository = authGraph.authRepository
}
