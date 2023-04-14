package com.renting.app.android.feature.property.quickfilter

import androidx.compose.foundation.border
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun PropertySelectableQuickFilter(
    isApplied: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Badge(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(16.dp),
            )
            .selectable(
                selected = isApplied,
                role = Role.Tab,
            ) { onClick() },
        backgroundColor = if (isApplied) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.background
        },
        contentColor = if (isApplied) {
            MaterialTheme.colors.onPrimary
        } else {
            MaterialTheme.colors.primary
        },
    ) {
        content()
    }
}
