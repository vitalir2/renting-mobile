package com.renting.app.feature.search.input

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.search.input.SearchInputComponent.Model

class DummySearchInputComponent : SearchInputComponent {

    override val models: Value<Model> = MutableValue(
        Model(
            query = "",
        )
    )

    override fun onFullFiltersClicked() {
        // Do nothing
    }

    override fun onQueryChanged(content: String) {
        // Do nothing
    }

    override fun onSearchClicked() {
        // Do nothing
    }
}
