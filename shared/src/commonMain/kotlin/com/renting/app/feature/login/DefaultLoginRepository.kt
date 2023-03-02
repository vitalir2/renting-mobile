package com.renting.app.feature.login

internal class DefaultLoginRepository : LoginRepository {

    override suspend fun login(login: String, password: String): String {
        return "" // TODO
    }
}
