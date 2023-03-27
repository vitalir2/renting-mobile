package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.UserRepository

interface AuthGraph {

    val authRepository: AuthRepository

    val userRepository: UserRepository
}
