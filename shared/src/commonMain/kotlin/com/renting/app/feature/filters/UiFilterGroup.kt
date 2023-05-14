package com.renting.app.feature.filters

data class UiFilterGroup(
    val name: String,
    val filters: List<UiFilter>,
) {

    constructor(name: String, filter: UiFilter) : this(name, listOf(filter))
}
