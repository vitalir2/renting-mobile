package com.renting.app.feature.root.di

import com.renting.app.feature.login.LoginGraph

interface RootGraph {

    val loginGraph: LoginGraph
}