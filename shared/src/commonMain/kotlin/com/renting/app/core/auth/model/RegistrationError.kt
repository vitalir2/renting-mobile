package com.renting.app.core.auth.model

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedRegistrationError")
sealed interface RegistrationError {
    data class ValidationFailed(
        val login: String? = null,
        val password: String? = null,
        val email: String? = null,
        val phoneNumber: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
    ) : RegistrationError
    object Unknown : RegistrationError
}
