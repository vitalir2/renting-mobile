package com.renting.app.android.feature.property

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters

@Composable
fun PropertyTypeQuickFilters(
    quickFilters: PropertyTypeQuickFilters?,
    onFilterSelected: (PropertyType) -> Unit,
    onSelectedFiltersCleared: () -> Unit,
) {
    if (quickFilters == null) {
        PropertySnippetTypeQuickFiltersSkeletons()
    } else {
        LazyRow(
            modifier = Modifier
                .selectableGroup(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(key = "All") {
                PropertyTypeQuickFilter(
                    isApplied = quickFilters.appliedFilter == null,
                    onSelected = { onSelectedFiltersCleared() },
                    name = "✅ All",
                )
            }
            items(quickFilters.list) { filter ->
                PropertyTypeQuickFilter(
                    filter = filter,
                    onFilterSelected = onFilterSelected,
                )
            }
        }
    }
}

@Composable
fun PropertySnippetTypeQuickFiltersSkeletons() {
    // TODO
}

@Preview
@Composable
private fun PropertyTypeQuickFiltersPreview() {
    RentingPreviewContainer {
        PropertyTypeQuickFilters(
            quickFilters = PropertyTypeQuickFilters(
                list = PropertyTypeQuickFilters.filtersOrder
                    .map(::PropertyTypeQuickFilter),
            ),
            onFilterSelected = {},
            onSelectedFiltersCleared = {},
        )
    }
}
