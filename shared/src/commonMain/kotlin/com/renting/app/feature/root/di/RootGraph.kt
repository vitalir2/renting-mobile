package com.renting.app.feature.root.di

import com.renting.app.feature.login.di.LoginGraph

interface RootGraph {

    val loginGraph: LoginGraph
}
