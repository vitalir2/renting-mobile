package com.renting.app.android.feature.auth

import com.renting.app.core.auth.model.UserInfo

val UserInfo.Companion.previewData: UserInfo
    get() = UserInfo(
        login = "login",
        firstName = "Jenny",
        lastName = "Kerry",
        avatar = null,
    )
