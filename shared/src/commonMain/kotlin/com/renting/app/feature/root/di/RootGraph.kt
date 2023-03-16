package com.renting.app.feature.root.di

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.feature.home.HomeGraph
import com.renting.app.feature.login.di.LoginGraph

interface RootGraph {

    val authGraph: AuthGraph

    val loginGraph: LoginGraph

    val homeGraph: HomeGraph
}
