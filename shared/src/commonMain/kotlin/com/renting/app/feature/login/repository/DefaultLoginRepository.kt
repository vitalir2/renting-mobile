package com.renting.app.feature.login.repository

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultLoginRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
) : LoginRepository {

    override suspend fun login(login: String, password: String): Either<Exception, String> = withContext(ioDispatcher) {
        val response = makeRequest(login, password)
        if (response.status.isSuccess()) {
            handleSuccess(response)
        } else {
            handleError(response)
        }
    }

    private suspend fun makeRequest(
        login: String,
        password: String
    ): HttpResponse = httpClient.post("/api/auth") {
        contentType(ContentType.Application.Json)
        setBody(
            LoginRequest(
                login = login,
                password = password,
            )
        )
    }

    private suspend fun handleSuccess(response: HttpResponse): Either.Right<String> {
        val responseBody = response.body<LoginResponse>()
        return responseBody.token.right()
    }

    private suspend fun handleError(response: HttpResponse): Either.Left<Exception> {
        val responseBody = response.body<ErrorResponse>()
        // TODO replace by our logger / interceptor
        DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
        return Exception(responseBody.message).left()
    }
}
