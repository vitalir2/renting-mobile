package com.renting.app.core.model

import com.renting.app.core.utils.Environment
import kotlin.experimental.ExperimentalObjCName
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedImage")
sealed interface Image {

    val id: String get() = when (this) {
        is Url -> path
    }
    data class Url(val path: String, val host: String? = null) : Image {

        // For Swift code
        constructor(path: String) : this(path, host = null)

        val fullUrl: String = "${host ?: Environment.PRODUCTION_IMAGE_HOST}$path"
    }
}
