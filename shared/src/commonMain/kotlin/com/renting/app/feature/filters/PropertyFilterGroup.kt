package com.renting.app.feature.filters

data class PropertyFilterGroup(
    val name: String,
    val filters: List<PropertyFilter>,
) {

    constructor(name: String, filter: PropertyFilter) : this(name, listOf(filter))
}
