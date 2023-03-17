package com.renting.app.core.auth.repository

data class InitUserData(
    val credentials: Credentials,
    val email: String,
    val phoneNumber: String,
    val fullName: FullName,
) {

    data class Credentials(
        val login: String,
        val password: String,
    )

    data class FullName(
        val firstName: String,
        val lastName: String,
        val patronymic: String?,
    )
}
