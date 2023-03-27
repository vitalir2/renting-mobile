package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.DefaultAuthRepository
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.DefaultUserRepository
import com.renting.app.core.auth.repository.UserRepository
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

    override val userRepository: UserRepository = DefaultUserRepository(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
    )
}
