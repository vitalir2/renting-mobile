package com.renting.app.core.auth.model

data class UserInfo(
    val login: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String,
) {

    val fullName: String
        get() = "$firstName $lastName"

    companion object
}
