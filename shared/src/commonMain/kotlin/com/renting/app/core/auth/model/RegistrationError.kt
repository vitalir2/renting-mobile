package com.renting.app.core.auth.model

import com.renting.app.core.form.TextField
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedRegistrationError")
sealed interface RegistrationError {
    data class ValidationFailed(
        val errors: Map<TextField.Id, String>,
    ) : RegistrationError
    object Unknown : RegistrationError
}
