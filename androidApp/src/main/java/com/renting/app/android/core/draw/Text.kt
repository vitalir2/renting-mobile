package com.renting.app.android.core.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawCenteredText(
    textLayoutResult: TextLayoutResult,
    color: Color,
) {
    val startX = (size.width - textLayoutResult.size.width) / 2
    val startY = (size.height - textLayoutResult.size.height) / 2
    drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(x = startX, y = startY),
        color = color,
    )
}
