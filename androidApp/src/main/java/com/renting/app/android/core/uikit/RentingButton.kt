package com.renting.app.android.core.uikit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun RentingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = !isLoading,
        shape = RoundedCornerShape(size = 100.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = MaterialTheme.colors.primary,
            disabledContentColor = MaterialTheme.colors.onPrimary,
        )

    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp),
                color = MaterialTheme.colors.onPrimary,
            )
        } else {
            content()
        }
    }
}

private data class RentingButtonPreview(
    val title: String,
    val isLoading: Boolean,
)

private class RentingButtonPreviewProvider : PreviewParameterProvider<RentingButtonPreview> {
    override val values: Sequence<RentingButtonPreview> = sequenceOf(
        RentingButtonPreview(
            title = "Title",
            isLoading = false,
        ),
        RentingButtonPreview(
            title = "Title",
            isLoading = true,
        ),
    )
}

@Preview
@Composable
private fun RentingButtonPreview(
    @PreviewParameter(RentingButtonPreviewProvider::class)
    data: RentingButtonPreview,
) {
    RentingPreviewContainer {
        RentingButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            isLoading = data.isLoading,
        ) {
            Text(text = data.title)
        }
    }
}
