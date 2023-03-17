package com.renting.app.feature.registration.di

import com.renting.app.core.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope

interface RegistrationGraph {

    val coroutineScope: CoroutineScope

    val authRepository: AuthRepository
}
