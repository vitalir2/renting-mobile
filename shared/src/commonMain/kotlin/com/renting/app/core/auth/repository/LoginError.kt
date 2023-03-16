package com.renting.app.core.auth.repository

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedLoginError")
sealed interface LoginError {
    object InvalidLoginOrPassword : LoginError
    object Unknown : LoginError
}
