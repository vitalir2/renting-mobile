package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.DefaultAuthRepository
import com.renting.app.core.auth.repository.AuthRepository
import com.russhwolf.settings.ObservableSettings
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

internal class DefaultAuthGraph(
    httpClient: HttpClient,
    ioDispatcher: CoroutineDispatcher,
    settings: ObservableSettings,
) : AuthGraph {

    override val authRepository: AuthRepository = DefaultAuthRepository(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )
}
