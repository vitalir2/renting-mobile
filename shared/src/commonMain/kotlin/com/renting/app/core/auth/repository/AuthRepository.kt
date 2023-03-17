package com.renting.app.core.auth.repository

import com.renting.app.core.monad.Either
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    val authToken: StateFlow<String?>

    fun isLoggedIn(): Boolean

    suspend fun login(
        login: String,
        password: String,
    ): Either<LoginError, AuthToken>

    suspend fun register(
        initUserData: InitUserData,
    ): Either<RegistrationError, AuthToken>

    suspend fun logout(): Either<Exception, Unit>
}
