package com.renting.app.feature.login

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
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
        return if (response.status.value in 200..299) {
            val responseBody = response.body<LoginResponse>()
            responseBody.token
        } else {
            val responseBody = response.body<ErrorResponse>()
            // TODO replace by our logger / interceptor
            DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
            "" // TODO
        }
    }
}
