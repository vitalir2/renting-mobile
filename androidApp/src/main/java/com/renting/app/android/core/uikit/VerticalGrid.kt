package com.renting.app.android.core.uikit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun VerticalGrid(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    require(columns > 0) { "Number of columns must be greater than 0" }

    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        var layoutHeight = 0
        val columnWidth = constraints.maxWidth / columns
        val measurableConstraints = constraints.copy(
            minWidth = columnWidth, maxWidth = columnWidth,
        )
        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(measurableConstraints).also { placeable ->
                val isFirstItemInRow = index % columns == 0
                if (isFirstItemInRow) {
                    layoutHeight = minOf(layoutHeight + placeable.height, constraints.maxHeight)
                }
            }
        }
        
        layout(constraints.maxWidth, layoutHeight) {
            var xPosition = 0
            var yPosition = 0
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(x = xPosition, y = yPosition)
                val isLastRowItem = (index + 1) % columns == 0
                if (isLastRowItem) {
                    xPosition = 0
                    yPosition = minOf(yPosition + placeable.height, constraints.maxHeight)
                } else {
                    xPosition += placeable.width
                }
            }
        }
    }
}
