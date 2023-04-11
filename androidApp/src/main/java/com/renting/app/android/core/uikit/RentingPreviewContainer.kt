package com.renting.app.android.core.uikit

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
fun RentingPreviewContainer(
    color: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit,
) {
    RentingTheme {
        Surface(color = color) {
            content()
        }
    }
}
