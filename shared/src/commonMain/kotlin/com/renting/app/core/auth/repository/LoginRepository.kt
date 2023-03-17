package com.renting.app.core.auth.repository

import com.renting.app.core.monad.Either
import kotlinx.coroutines.flow.StateFlow

interface LoginRepository {

    val authToken: StateFlow<String?>

    fun isLoggedIn(): Boolean

    suspend fun login(
        login: String,
        password: String,
    ): Either<LoginError, String>

    suspend fun logout(): Either<Exception, Unit>
}
