package com.renting.app.android.app

import com.renting.app.feature.root.di.RootGraph

object AppGraph {

    private var innerRootGraph: RootGraph? = null
    val rootGraph: RootGraph
        get() = requireNotNull(innerRootGraph)

    fun setGraph(rootGraph: RootGraph) {
        innerRootGraph = rootGraph
    }
}
