package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.DefaultLoginRepository
import com.renting.app.core.auth.repository.LoginRepository
import com.russhwolf.settings.ObservableSettings
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

internal class DefaultAuthGraph(
    httpClient: HttpClient,
    ioDispatcher: CoroutineDispatcher,
    settings: ObservableSettings,
) : AuthGraph {

    override val loginRepository: LoginRepository = DefaultLoginRepository(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )
}
