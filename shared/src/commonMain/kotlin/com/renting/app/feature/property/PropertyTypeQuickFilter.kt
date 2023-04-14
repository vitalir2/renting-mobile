package com.renting.app.feature.property

data class PropertyTypeQuickFilter(
    val type: PropertyType,
    val isApplied: Boolean = false,
) {

    fun applyTo(snippets: List<PropertySnippet>): List<PropertySnippet> {
        return snippets.filter { it.type == type }
    }
}
