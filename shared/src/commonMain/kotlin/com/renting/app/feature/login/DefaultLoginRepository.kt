package com.renting.app.feature.login

import io.ktor.client.*

internal class DefaultLoginRepository(
    private val httpClient: HttpClient,
) : LoginRepository {

    override suspend fun login(login: String, password: String): String {
        return "" // TODO
    }
}
