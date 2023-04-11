package com.renting.app.feature.search

interface SearchInputComponent {

    fun onFullFiltersClicked()

    fun onContentChanged(content: String)

    fun onSearchClicked()
}
