package com.renting.app.core.auth.model

data class UserInfo(
    val login: String,
    val imageUrl: String,
) {

    // TODO
    val fullName: String
        get() = "Full name"
}
