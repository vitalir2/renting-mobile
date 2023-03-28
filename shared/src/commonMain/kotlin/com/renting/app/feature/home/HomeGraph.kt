package com.renting.app.feature.home

import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.UserRepository
import kotlinx.coroutines.CoroutineScope

interface HomeGraph {

    val coroutineScope: CoroutineScope

    val authRepository: AuthRepository

    val userRepository: UserRepository
}
