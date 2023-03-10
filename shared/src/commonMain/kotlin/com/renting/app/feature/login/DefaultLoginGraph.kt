package com.renting.app.feature.login

import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

internal class DefaultLoginGraph(
    httpClient: HttpClient,
    ioDispatcher: CoroutineDispatcher,
) : LoginGraph {

    override val loginRepository: LoginRepository = DefaultLoginRepository(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
    )
}