package com.renting.app.feature.registration

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal class DefaultRegistrationGraph(
    authGraph: AuthGraph,
) : RegistrationGraph {

    override val coroutineScope: CoroutineScope = MainScope()

    override val loginRepository: LoginRepository = authGraph.loginRepository
}
