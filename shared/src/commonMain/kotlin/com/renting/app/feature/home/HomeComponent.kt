package com.renting.app.feature.home

import com.arkivanov.decompose.value.Value
import com.renting.app.core.auth.model.UserInfo

interface HomeComponent {

    val models: Value<Model>

    fun logout()

    data class Model(
        val userInfo: UserInfo?,
    )
}
