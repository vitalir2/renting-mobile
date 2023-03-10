package com.renting.app.feature.login.repository

import com.renting.app.core.monad.Either
import kotlinx.coroutines.flow.StateFlow

interface LoginRepository {

    val authToken: StateFlow<String?>

    suspend fun login(
        login: String,
        password: String,
    ): Either<Exception, String>
}
