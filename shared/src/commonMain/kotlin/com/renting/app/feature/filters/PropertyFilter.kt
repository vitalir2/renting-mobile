package com.renting.app.feature.filters

import com.renting.app.feature.property.PropertySnippet

interface PropertyFilter {

    fun isIncluded(snippet: PropertySnippet): Boolean
}

abstract class ToggleablePropertyFilter<T>(
    val toggles: List<Toggle<T>>,
    val activeToggle: Toggle<T>? = null,
) : PropertyFilter {

    abstract val predicate: (T, snippet: PropertySnippet) -> Boolean

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        return if (activeToggle == null) {
            true
        } else {
            predicate(activeToggle.value, snippet)
        }
    }

    data class Toggle<T>(val name: String, val value: T)
}

data class PricePropertyFilter(
    val range: IntRange = DEFAULT_RANGE,
) : PropertyFilter {

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        return snippet.price in range
    }

    companion object {
        val DEFAULT_RANGE = 0..100
    }
}

data class PropertyLocationSelector(
    val values: List<String>,
    val selectedValue: String,
) : PropertyFilter {

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        return selectedValue in snippet.location
    }
}
