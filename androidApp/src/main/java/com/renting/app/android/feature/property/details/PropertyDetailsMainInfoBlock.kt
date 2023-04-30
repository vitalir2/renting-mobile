package com.renting.app.android.feature.property.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails

@Composable
fun PropertyDetailsMainInfoBlock(mainInfo: ComponentPropertyDetails.MainInfo) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = mainInfo.price,
            style = MaterialTheme.typography.subtitle1,
        )
        Row {
            Column {
                Text(
                    text = mainInfo.area,
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    text = "area",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PropertyDetailsMainInfoBlockPreview() {
    RentingPreviewContainer {
        PropertyDetailsMainInfoBlock(
            mainInfo = ComponentPropertyDetails.MainInfo(
                price = "10800 ₽",
                area = "50 sqm",
            ),
        )
    }
}
