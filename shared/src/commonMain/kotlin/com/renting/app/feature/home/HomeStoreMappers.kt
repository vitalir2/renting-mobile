package com.renting.app.feature.home

internal object HomeStoreMappers {

    val stateToModel: HomeStore.State.() -> HomeComponent.Model = {
        HomeComponent.Model(
            userInfo = userInfo,
        )
    }
}
