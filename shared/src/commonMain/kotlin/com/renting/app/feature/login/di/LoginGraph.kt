package com.renting.app.feature.login.di

import com.renting.app.feature.login.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope

interface LoginGraph {

    val coroutineScope: CoroutineScope

    val loginRepository: LoginRepository
}
