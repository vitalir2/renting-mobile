package com.renting.app.feature.property.model

import com.renting.app.core.auth.model.UserInfo

data class FamilyHouse(
    override val id: PropertyId,
    override val location: String,
    override val owner: PropertyOwner,
    override val area: Float,
    override val description: String
) : Property
