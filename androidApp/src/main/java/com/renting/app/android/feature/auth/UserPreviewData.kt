package com.renting.app.android.feature.auth

import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.model.Image

val UserInfo.Companion.previewData: UserInfo
    get() = UserInfo(
        login = "login",
        firstName = "Jenny",
        lastName = "Kerry",
        avatar = Image.Url("https://phonoteka.org/uploads/posts/2022-09/" +
                "1663805280_49-phonoteka-org-p-billi-kherrington-oboi-krasivo-56.jpg"),
    )
