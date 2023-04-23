package com.renting.app.feature.search.input

import com.arkivanov.decompose.value.Value

interface SearchInputComponent {

    val models: Value<Model>
    fun onFullFiltersClicked()

    fun onQueryChanged(content: String)

    fun onSearchClicked()

    data class Model(
        val query: String,
    )
}
