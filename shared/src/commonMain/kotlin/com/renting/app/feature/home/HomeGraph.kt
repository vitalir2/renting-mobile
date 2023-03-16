package com.renting.app.feature.home

import com.renting.app.core.auth.repository.LoginRepository

interface HomeGraph {

    val loginRepository: LoginRepository
}
