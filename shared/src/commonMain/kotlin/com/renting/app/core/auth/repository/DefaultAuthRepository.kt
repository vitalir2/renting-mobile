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
import com.renting.app.core.form.TextField
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

    override suspend fun login(
        login: String,
        password: String,
    ): Either<LoginError, AuthToken> = withContext(ioDispatcher) {
        val response = makeLoginRequest(login, password)
        if (response.status.isSuccess()) {
            handleSuccessfulLogin(response)
        } else {
            handleLoginError(response)
        }
    }

    private suspend fun makeLoginRequest(
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

    private suspend fun handleSuccessfulLogin(response: HttpResponse): Either.Right<AuthToken> {
        val token = response.body<LoginResponse>().token
        saveToken(token)
        return token.right()
    }

    private suspend fun handleLoginError(response: HttpResponse): Either.Left<LoginError> {
        val responseBody = response.body<LoginErrorResponse>()
        // TODO replace by our logger / interceptor
        DefaultLogger.log("Error: message=${responseBody.message},status=${responseBody.status}")
        return when (response.status) {
            HttpStatusCode.Conflict -> LoginError.InvalidLoginOrPassword // 409, TODO ask backend to fix it
            else -> LoginError.Unknown
        }.left()
    }

    override suspend fun register(
        initUserData: InitUserData,
    ): Either<RegistrationError, AuthToken> = withContext(ioDispatcher) {
        val response = makeRegistrationRequest(initUserData)
        if (response.status.isSuccess()) {
            handleSuccessfulRegistration(response)
        } else {
            handleRegistrationError(response)
        }
    }

    private suspend fun makeRegistrationRequest(initUserData: InitUserData) =
        httpClient.post("/api/signup") {
            contentType(ContentType.Application.Json)
            setBody(initUserData.toRequestModel())
        }

    private suspend fun handleSuccessfulRegistration(response: HttpResponse): Either.Right<AuthToken> {
        val token = response.body<RegistrationResponse>().token
        saveToken(token)
        return token.right()
    }

    private suspend fun handleRegistrationError(response: HttpResponse) =
        when (response.status) {
            HttpStatusCode.BadRequest -> {
                val errors = response.body<RegistrationErrorResponse>().errors
                errors.toDomainModel()
            }
            else -> RegistrationError.Unknown
        }.left()

    override suspend fun logout(): Either<Exception, Unit> {
        dropToken()
        return Unit.right()
    }

    private fun saveToken(token: AuthToken) {
        settings.putString(SettingKey.AUTH_TOKEN, token)
    }

    private fun dropToken() {
        settings.remove(SettingKey.AUTH_TOKEN)
    }

    companion object {
        private fun InitUserData.toRequestModel(): RegistrationRequest = RegistrationRequest(
            login = credentials.login,
            password = credentials.password,
            email = email,
            phoneNumber = phoneNumber,
            firstName = fullName.firstName,
            lastName = fullName.lastName,
        )

        private fun RegistrationErrors.toDomainModel(): RegistrationError.ValidationFailed {
            val errors = mutableMapOf<TextField.Kind, String>()
            if (login != null) errors[TextField.Kind.LOGIN] = login
            if (password != null) errors[TextField.Kind.PASSWORD] = password
            if (email != null) errors[TextField.Kind.EMAIL] = email
            if (phoneNumber != null) errors[TextField.Kind.PHONE_NUMBER] = phoneNumber
            if (firstName != null) errors[TextField.Kind.FIRST_NAME] = firstName
            if (lastName != null) errors[TextField.Kind.LAST_NAME] = lastName

            return RegistrationError.ValidationFailed(
                errors = errors.mapKeys { (key, _) -> TextField.Id(key) }.toMap(),
            )
        }
    }
}
