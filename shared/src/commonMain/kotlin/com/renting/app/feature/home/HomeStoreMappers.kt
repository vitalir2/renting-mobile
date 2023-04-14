package com.renting.app.feature.home

internal object HomeStoreMappers {

    val stateToModel: HomeStore.State.() -> HomeComponent.Model = {
        val appliedFilter = recommendationQuickFilters?.appliedFilter
        HomeComponent.Model(
            userInfo = userInfo,
            recommendations = appliedFilter?.applyTo(recommendations) ?: recommendations,
            recommendationQuickFilters = recommendationQuickFilters,
        )
    }
}
