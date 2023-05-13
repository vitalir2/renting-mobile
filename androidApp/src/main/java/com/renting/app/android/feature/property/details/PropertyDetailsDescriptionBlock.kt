package com.renting.app.android.feature.property.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

private const val DESCRIPTION_MAX_LINES_COLLAPSED = 4

@Composable
fun PropertyDetailsDescriptionBlock(
    buildingInfo: ComponentPropertyDetails.BuildingInfo?,
    description: String,
) {
    Column {
        buildingInfo?.let {
            BuildingDescription(buildingInfo)
            Gap(8.dp)
        }
        GeneralDescription(description)
    }
}

@Composable
private fun GeneralDescription(
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

@Composable
private fun BuildingDescription(
    buildingInfo: ComponentPropertyDetails.BuildingInfo,
) {
    Column {
        Text(
            text = "About building",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
        )
        Gap(8.dp)
        BuildingFeature(title = "Building year", value = buildingInfo.buildingYear)
        BuildingFeature(title = "Number of floors", value = buildingInfo.numberOfFloors)
        buildingInfo.buildingType?.let { type ->
            BuildingFeature(title = "Type", value = type)
        }
        BuildingFeature(title = "Material", value = buildingInfo.material)
    }
}

@Composable
private fun BuildingFeature(
    title: String,
    value: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondaryVariant,
        )
        Text(
            text = value,
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview
@Composable
private fun PropertyDetailsDescriptionBlockPreview() {
    RentingPreviewContainer {
        PropertyDetailsDescriptionBlock(
            buildingInfo = ComponentPropertyDetailsPreviews.apartment.buildingInfo,
            description = ComponentPropertyDetailsPreviews.apartment.description,
        )
    }
}
