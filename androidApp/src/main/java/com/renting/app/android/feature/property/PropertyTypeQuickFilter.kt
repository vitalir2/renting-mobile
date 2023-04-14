package com.renting.app.android.feature.property

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilter

@Composable
fun PropertyTypeQuickFilter(
    filter: PropertyTypeQuickFilter,
    onFilterSelected: (PropertyType) -> Unit,
) {
    PropertySelectableQuickFilter(
        isApplied = filter.isApplied,
        onClick = {
            onFilterSelected(filter.type)
        },
    ) {
        Text(
            text = filter.getName(),
            modifier = Modifier
                .padding(4.dp),
            style = MaterialTheme.typography.caption,
        )
    }
}

private fun PropertyTypeQuickFilter.getName(): String {
    return when (type) {
        PropertyType.FAMILY_HOUSE -> "🏡 House"
        PropertyType.LAND -> "🏞️ Land"
        PropertyType.APARTMENT -> "🏢 Apartment"
    }
}

private class PropertySnippetTypeQuickFilterPreviewProvider
    : PreviewParameterProvider<PropertyTypeQuickFilter> {

    override val values: Sequence<PropertyTypeQuickFilter> =
        sequenceOf(
            PropertyTypeQuickFilter(type = PropertyType.FAMILY_HOUSE),
            PropertyTypeQuickFilter(
                type = PropertyType.LAND,
                isApplied = true,
            ),
        )
}

@Preview
@Composable
private fun PropertySnippetTypeQuickFilterPreview(
    @PreviewParameter(PropertySnippetTypeQuickFilterPreviewProvider::class)
    filter: PropertyTypeQuickFilter,
) {
    RentingPreviewContainer {
        PropertyTypeQuickFilter(
            filter = filter,
            onFilterSelected = {},
        )
    }
}
