package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilters

internal interface HomeStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ToggleTypeQuickFilter(val type: PropertyType) : Intent

        object Logout : Intent
        object ClearTypeQuickFiltersSelection : Intent
    }

    sealed interface Label {
        object LoggedOutSuccessfully : Label
    }

    data class State(
        val userInfo: UserInfo? = null,
        val recommendations: List<PropertySnippet> = emptyList(),
        val recommendationQuickFilters: PropertyTypeQuickFilters? = null,
    )
}
