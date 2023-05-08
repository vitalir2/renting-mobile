package com.renting.app.android.feature.property.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingPreviewContainer

@Composable
fun PropertyDetailsBookingFooter(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .shadow(2.dp),
    ) {
        RentingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                Toast
                    .makeText(context, "In development \uD83D\uDE09", Toast.LENGTH_SHORT)
                    .show()
            },
        ) {
            Text("Booking Now")
        }
    }
}

@Preview
@Composable
private fun PropertyDetailsBookingFooterPreview() {
    RentingPreviewContainer {
        PropertyDetailsBookingFooter(
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
