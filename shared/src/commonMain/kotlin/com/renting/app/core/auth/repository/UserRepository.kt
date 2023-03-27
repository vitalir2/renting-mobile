package com.renting.app.core.auth.repository

import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.monad.Either

interface UserRepository {

    suspend fun getUserInfo(): Either<Exception, UserInfo>
}
