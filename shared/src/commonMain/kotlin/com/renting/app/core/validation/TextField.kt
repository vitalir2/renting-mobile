package com.renting.app.core.validation

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedTextField")
data class TextField(
    val id: Id,
    val value: String = "",
    val error: String? = null,
) {

    enum class Id {
        LOGIN,
        PASSWORD,
        EMAIL,
        PHONE_NUMBER,
        FIRST_NAME,
        LAST_NAME,
    }
}
