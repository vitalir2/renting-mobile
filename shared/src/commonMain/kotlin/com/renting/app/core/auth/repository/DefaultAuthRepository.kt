package com.renting.app.core.auth.repository

import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.renting.app.core.auth.model.AuthToken
import com.renting.app.core.auth.model.InitUserData
import com.renting.app.core.auth.model.LoginError
import com.renting.app.core.auth.model.RegistrationError
import com.renting.app.core.auth.repository.external.LoginErrorResponse
import com.renting.app.core.auth.repository.external.LoginRequest
import com.renting.app.core.auth.repository.external.LoginResponse
import com.renting.app.core.auth.repository.external.RegistrationErrorResponse
import com.renting.app.core.auth.repository.external.RegistrationErrors
import com.renting.app.core.auth.repository.external.RegistrationRequest
import com.renting.app.core.auth.repository.external.RegistrationResponse
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

internal class DefaultAuthRepository(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val settings: ObservableSettings,
) : AuthRepository {

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

    override fun isLoggedIn(): Boolean {
        return authToken.value != null
    }

    override suspend fun login(login: String, password: String): Either<LoginError, AuthToken> =
        withContext(ioDispatcher) {
            val response = makeRequest(login, password)
            if (response.status.isSuccess()) {
                handleSuccess(response)
            } else {
                handleError(response)
            }
        }

    override suspend fun register(
        initUserData: InitUserData,
    ): Either<RegistrationError, AuthToken> {
        return withContext(ioDispatcher) {
            val response = httpClient.post("/api/signup") {
                contentType(ContentType.Application.Json)
                setBody(initUserData.toRequestModel())
            }
            if (response.status.isSuccess()) {
                val token = response.body<RegistrationResponse>().token
                settings.putString(SettingKey.AUTH_TOKEN, token)
                token.right()
            } else {
                when (response.status.value) {
                    400 -> {
                        val errors = response.body<RegistrationErrorResponse>().errors
                        errors.toDomainModel()
                    }
                    else -> RegistrationError.Unknown
                }.left()
            }
        }
    }

    override suspend fun logout(): Either<Exception, Unit> {
        settings.remove(SettingKey.AUTH_TOKEN)
        return Unit.right()
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
        val responseBody = response.body<LoginErrorResponse>()
        // TODO replace by our logger / interceptor
        DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
        return when (response.status.value) {
            409 -> LoginError.InvalidLoginOrPassword
            else -> LoginError.Unknown
        }.left()
    }

    companion object {
        private fun InitUserData.toRequestModel(): RegistrationRequest {
            return RegistrationRequest(
                login = credentials.login,
                password = credentials.password,
                email = email,
                phoneNumber = phoneNumber,
                firstName = fullName.firstName,
                lastName = fullName.lastName,
            )
        }

        private fun RegistrationErrors.toDomainModel(): RegistrationError.ValidationFailed {
            return RegistrationError.ValidationFailed(
                login = login,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                firstName = firstName,
                lastName = lastName,
            )
        }
    }
}
