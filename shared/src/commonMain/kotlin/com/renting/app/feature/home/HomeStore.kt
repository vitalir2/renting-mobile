package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Store
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import com.renting.app.feature.property.PropertySnippet

internal interface HomeStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ChangeSearchInput(val content: String) : Intent
        object Logout : Intent
    }

    sealed interface Label {
        object LoggedOutSuccessfully : Label
    }

    data class State(
        val userInfo: UserInfo? = null,
        val recommendations: List<PropertySnippet> = emptyList(),
        val searchInputContent: String = "",
    )
}
