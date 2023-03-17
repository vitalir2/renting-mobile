package com.renting.app.feature.login.di

import com.renting.app.core.auth.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope

interface LoginGraph {

    val coroutineScope: CoroutineScope

    val authRepository: AuthRepository
}
