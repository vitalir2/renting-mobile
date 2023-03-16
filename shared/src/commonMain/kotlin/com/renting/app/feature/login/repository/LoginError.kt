package com.renting.app.feature.login.repository

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("SharedLoginError")
sealed interface LoginError {
    object InvalidLoginOrPassword : LoginError
    object Unknown : LoginError
}
