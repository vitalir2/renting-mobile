package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.AuthRepository

interface AuthGraph {

    val authRepository: AuthRepository
}
