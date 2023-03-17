package com.renting.app.feature.home

import com.renting.app.core.auth.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope

interface HomeGraph {

    val coroutineScope: CoroutineScope

    val loginRepository: LoginRepository
}
