package com.renting.app.core.model

import com.renting.app.core.utils.Environment
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedImage")
sealed interface Image {
    data class Url(val path: String) : Image {
        val fullUrl = "${Environment.PRODUCTION_IMAGE_HOST}$path"
    }
}
