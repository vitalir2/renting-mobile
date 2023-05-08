package com.renting.app.android.feature.property.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

@Composable
fun PropertyDetailsLocationBlock(
    location: String,
) {
    Column {
        Text(
            text = "Location",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
        )
        Gap(16.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colors.primary,
                ),
            )
            Gap(8.dp)
            Text(
                text = location,
                fontWeight = FontWeight.Light,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    RentingPreviewContainer {
        PropertyDetailsLocationBlock(
            location = ComponentPropertyDetailsPreviews.land.location,
        )
    }
}
