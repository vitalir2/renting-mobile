package com.renting.app.android.feature.property.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

private const val DESCRIPTION_MAX_LINES_COLLAPSED = 4

@Composable
fun PropertyDetailsDescriptionBlock(
    description: String,
) {
    var isDescriptionCanBeExpanded by remember { mutableStateOf(false) }
    var isDescriptionExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Overview",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
        )
        Gap(8.dp)
        Text(
            text = description,
            maxLines = if (isDescriptionExpanded) {
                Int.MAX_VALUE
            } else {
                DESCRIPTION_MAX_LINES_COLLAPSED
            },
            onTextLayout = { result ->
                isDescriptionCanBeExpanded = result.didOverflowHeight
            },
        )

        if (isDescriptionCanBeExpanded) {
            Text(
                text = if (isDescriptionExpanded) {
                    "Read less"
                } else {
                    "Read more..."
                },
                modifier = Modifier
                    .clickable { isDescriptionExpanded = !isDescriptionExpanded },
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}

@Preview
@Composable
private fun PropertyDetailsDescriptionBlockPreview() {
    RentingPreviewContainer {
        PropertyDetailsDescriptionBlock(
            description = ComponentPropertyDetailsPreviews.land.description,
        )
    }
}
