package com.renting.app.feature.login.repository

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.left
import com.renting.app.core.monad.right
import com.renting.app.core.settings.SettingKey
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SettingsListener
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

internal class DefaultLoginRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val settings: ObservableSettings,
) : LoginRepository {

    // Holds a strong reference to not be collected by GC
    @Suppress("UNUSED", "UnusedPrivateMember")
    private val authTokenSettingListener: SettingsListener =
        settings.addStringOrNullListener(SettingKey.AUTH_TOKEN) { token ->
            _authToken.value = token
        }

    private val _authToken = MutableStateFlow(
        value = settings.getStringOrNull(SettingKey.AUTH_TOKEN),
    )
    override val authToken: StateFlow<String?> = _authToken.asStateFlow()

    override suspend fun login(login: String, password: String): Either<LoginError, String> =
        withContext(ioDispatcher) {
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
        val token = response.body<LoginResponse>().token
        settings.putString(SettingKey.AUTH_TOKEN, token)
        return token.right()
    }

    private suspend fun handleError(response: HttpResponse): Either.Left<LoginError> {
        val responseBody = response.body<ErrorResponse>()
        // TODO replace by our logger / interceptor
        DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
        return when (response.status.value) {
            409 -> LoginError.InvalidLoginOrPassword
            else -> LoginError.Unknown
        }.left()
    }
}
