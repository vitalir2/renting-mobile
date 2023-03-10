package com.renting.app.feature.root

import com.renting.app.feature.login.LoginGraph

interface RootGraph {

    val loginGraph: LoginGraph
}