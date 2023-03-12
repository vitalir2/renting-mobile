package com.renting.app.android.core.uikit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
fun RentingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(size = 100.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp,
        ),
    ) {
        content()
    }
}

@Preview
@Composable
private fun RentingButtonPreview() {
    RentingTheme() {
        Box(modifier = Modifier.fillMaxSize()) {
            RentingButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            ) {
                Text(text = "Sign in")
            }
        }
    }
}
