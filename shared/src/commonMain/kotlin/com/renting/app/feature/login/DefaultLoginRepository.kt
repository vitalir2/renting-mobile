package com.renting.app.feature.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class DefaultLoginRepository(
    private val httpClient: HttpClient,
) : LoginRepository {

    override suspend fun login(login: String, password: String): String {
        val response = httpClient.post("/api/auth") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    login = login,
                    password = password,
                )
            )
        }
        val responseBody = response.body<LoginResponse>()
        return responseBody.token
    }
}
