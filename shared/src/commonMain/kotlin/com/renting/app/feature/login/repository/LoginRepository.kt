package com.renting.app.feature.login.repository

import com.renting.app.core.monad.Either

interface LoginRepository {

    suspend fun login(
        login: String,
        password: String,
    ): Either<Exception, String>
}
