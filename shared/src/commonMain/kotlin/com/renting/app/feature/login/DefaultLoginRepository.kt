package com.renting.app.feature.login

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultLoginRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
) : LoginRepository {

    override suspend fun login(login: String, password: String): Either<Exception, String> = withContext(ioDispatcher) {
        val response = httpClient.post("/api/auth") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    login = login,
                    password = password,
                )
            )
        }
        return@withContext if (response.status.value in 200..299) {
            val responseBody = response.body<LoginResponse>()
            responseBody.token.right()
        } else {
            val responseBody = response.body<ErrorResponse>()
            // TODO replace by our logger / interceptor
            DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
            Exception(responseBody.message).left()
        }
    }
}
