package com.renting.app.android.core.uikit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme

// Scope param is used to define behavior
@Suppress("UNUSED")
@Composable
@NonRestartableComposable
fun ColumnScope.Gap(value: Dp) {
    Spacer(Modifier.height(value))
}

@Suppress("UNUSED")
@Composable
@NonRestartableComposable
fun RowScope.Gap(value: Dp) {
    Spacer(Modifier.width(value))
}

@Preview
@Composable
private fun ColumnGapPreview() {
    RentingTheme {
        Column {
            Text("Hello")
            Gap(24.dp)
            Text("World")
        }
    }
}
