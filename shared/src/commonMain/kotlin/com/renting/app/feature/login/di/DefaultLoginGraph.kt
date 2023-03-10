package com.renting.app.feature.login.di

import com.renting.app.feature.login.repository.DefaultLoginRepository
import com.renting.app.feature.login.repository.LoginRepository
import com.russhwolf.settings.Settings
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

internal class DefaultLoginGraph(
    httpClient: HttpClient,
    ioDispatcher: CoroutineDispatcher,
    settings: Settings,
) : LoginGraph {

    override val loginRepository: LoginRepository = DefaultLoginRepository(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )
}
