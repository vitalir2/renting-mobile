package com.renting.app.core.auth.di

import com.renting.app.core.auth.repository.LoginRepository

interface AuthGraph {

    val loginRepository: LoginRepository
}
