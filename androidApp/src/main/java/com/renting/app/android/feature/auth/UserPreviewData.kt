package com.renting.app.android.feature.auth

import com.renting.app.core.auth.model.UserInfo

val UserInfo.Companion.previewData: UserInfo
    get() = UserInfo(
        login = "login",
        firstName = "Jenny",
        lastName = "Kerry",
        imageUrl = "https://phonoteka.org/uploads/posts/2022-09/" +
                "1663805280_49-phonoteka-org-p-billi-kherrington-oboi-krasivo-56.jpg",
    )
