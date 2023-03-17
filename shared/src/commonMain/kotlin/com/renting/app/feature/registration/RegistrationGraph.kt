package com.renting.app.feature.registration

import com.renting.app.core.auth.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope

interface RegistrationGraph {

    val coroutineScope: CoroutineScope

    val loginRepository: LoginRepository
}
