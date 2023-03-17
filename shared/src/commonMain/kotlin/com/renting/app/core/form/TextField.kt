package com.renting.app.core.form

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedTextField")
data class TextField(
    val id: Id,
    val value: String = "",
    val error: String? = null,
) {

    constructor(kind: Kind) : this(Id(kind))

    data class Id(
        val kind: Kind,
        val index: Int = FIRST_INDEX,
    ) {

        companion object {
            private const val FIRST_INDEX = 0
        }
    }

    enum class Kind {
        LOGIN,
        PASSWORD,
        EMAIL,
        PHONE_NUMBER,
        FIRST_NAME,
        LAST_NAME,
    }
}
