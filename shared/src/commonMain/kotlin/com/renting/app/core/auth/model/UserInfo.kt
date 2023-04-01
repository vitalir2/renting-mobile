package com.renting.app.core.auth.model

import com.renting.app.core.model.Image

data class UserInfo(
    val login: String,
    val firstName: String,
    val lastName: String,
    val avatar: Image?,
) {

    val fullName: String
        get() = "$firstName $lastName"

    companion object
}
