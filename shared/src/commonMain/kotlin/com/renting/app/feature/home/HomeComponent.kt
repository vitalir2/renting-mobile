package com.renting.app.feature.home

import com.arkivanov.decompose.value.Value
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.SearchInputComponent

interface HomeComponent {

    val searchInput: SearchInputComponent

    val models: Value<Model>

    fun onRecommendationClicked(id: Long)

    fun logout()

    fun onTypeQuickFilterClicked(type: PropertyType)

    fun clearTypeQuickFiltersSelection()

    data class Model(
        val userInfo: UserInfo?,
        val recommendations: List<PropertySnippet>,
        val recommendationQuickFilters: PropertyTypeQuickFilters?,
    )
}
