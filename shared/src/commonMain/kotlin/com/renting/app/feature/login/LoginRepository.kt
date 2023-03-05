package com.renting.app.feature.login

import com.renting.app.core.monad.Either

internal interface LoginRepository {

    suspend fun login(
        login: String,
        password: String,
    ): Either<Exception, String>
}
