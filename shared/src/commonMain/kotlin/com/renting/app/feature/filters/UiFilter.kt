package com.renting.app.feature.filters

interface UiFilter {

    val id: String
}

data class ToggleableFilter(
    override val id: String,
    val toggles: List<Toggle>,
    val activeToggle: Toggle?,
) : UiFilter {

    data class Toggle(
        val name: String,
    )
}

data class SlideFilter(
    override val id: String,
    val range: IntRange,
) : UiFilter

data class TextSelectorFilter(
    override val id: String,
    val availableValues: List<String>,
    val value: String,
) : UiFilter
