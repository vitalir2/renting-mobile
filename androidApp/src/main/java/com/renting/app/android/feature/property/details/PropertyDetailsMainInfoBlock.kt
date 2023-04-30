package com.renting.app.android.feature.property.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.model.PropertyDetails
import com.renting.app.feature.property.model.PropertyOffer
import kotlin.math.roundToInt

private val PropertyOffer.formattedPrice: String
    get() {
        val price = "$price ₽"
        val type = when (priceType) {
            PropertyOffer.PriceType.PER_NIGHT -> "/ night"
        }
        return "$price $type"
    }

@Composable
fun PropertyDetailsMainInfoBlock(details: PropertyDetails) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = details.propertyOffer.formattedPrice,
            style = MaterialTheme.typography.subtitle1,
        )
        Row {
            Column {
                Text(
                    text = "${details.property.area.roundToInt()} ㎡",
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
            details = PropertyDetails.preview,
        )
    }
}
