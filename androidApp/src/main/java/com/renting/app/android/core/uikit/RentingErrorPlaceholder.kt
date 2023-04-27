package com.renting.app.android.core.uikit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RentingErrorPlaceholder(
    action: RentingButtonAction,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "Oops, something went wrong",
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
        )
        Gap(4.dp)
        Text(
            text = "Please try again, or return to the previous page",
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
        Gap(8.dp)
        RentingButton(
            onClick = action.handler,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(action.title)
        }
    }
}

data class RentingButtonAction(
    val title: String,
    val handler: () -> Unit,
)

@Preview
@Composable
private fun RentingErrorPlaceholderPreview() {
    RentingPreviewContainer {
        RentingErrorPlaceholder(
            action = RentingButtonAction(
                title = "Retry",
                handler = {},
            )
        )
    }
}
