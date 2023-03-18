package com.renting.app.android.core.uikit

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.renting.app.android.core.brandbook.RentingTheme

@Composable
fun RentingPreviewContainer(
    content: @Composable () -> Unit,
) {
    RentingTheme {
        Surface {
            content()
        }
    }
}
