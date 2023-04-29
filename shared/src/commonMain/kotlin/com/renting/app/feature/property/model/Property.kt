package com.renting.app.feature.property.model

import com.renting.app.core.auth.model.UserInfo

sealed interface Property {
    val id: PropertyId
    val location: String
    val owner: UserInfo
    val area: Int
    val description: String
}
