package com.renting.app.feature.login

internal interface LoginRepository {
    suspend fun login(
        login: String,
        password: String,
    ): String
}
