package com.renting.app.feature.filters

import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType

interface PropertyFilter {
    val id: String

    fun isIncluded(snippet: PropertySnippet): Boolean
}

abstract class SelectionPropertyFilter<T>(
    override val id: String,
    open val toggles: List<Toggle<T>>,
    open val activeToggle: Toggle<T>? = null,
) : PropertyFilter {

    abstract val predicate: (T, snippet: PropertySnippet) -> Boolean

    abstract fun setActiveToggle(name: String): SelectionPropertyFilter<T>

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        val activeToggle = activeToggle
        return if (activeToggle == null) {
            true
        } else {
            predicate(activeToggle.value, snippet)
        }
    }

    data class Toggle<T>(val name: String, val value: T)
}

data class PricePropertyFilter(
    override val id: String,
    val range: IntRange = DEFAULT_RANGE,
) : PropertyFilter {

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        return snippet.price in range
    }

    companion object {
        val DEFAULT_RANGE = 0..100
    }
}

data class PropertyLocationChooser(
    override val id: String,
    val values: List<String>,
    val selectedValue: String,
) : PropertyFilter {

    override fun isIncluded(snippet: PropertySnippet): Boolean {
        return selectedValue in snippet.location
    }
}

data class PropertyTypeFilter(
    override val id: String,
    override val toggles: List<Toggle<PropertyType>>,
    override val activeToggle: Toggle<PropertyType>? = null,
): SelectionPropertyFilter<PropertyType>(
    id = id,
    toggles = toggles,
    activeToggle = activeToggle,
) {

    override val predicate: (
        PropertyType,
        snippet: PropertySnippet,
    ) -> Boolean = { type, snippet -> type == snippet.type }

    override fun setActiveToggle(name: String): PropertyTypeFilter {
        return copy(
            activeToggle = toggles.firstOrNull { it.name == name },
        )
    }
}
