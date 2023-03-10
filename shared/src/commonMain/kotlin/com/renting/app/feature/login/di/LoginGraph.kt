package com.renting.app.feature.login.di

import com.renting.app.feature.login.repository.LoginRepository

interface LoginGraph {

    val loginRepository: LoginRepository
}
