package com.renting.app.android.core.uikit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import com.renting.app.android.core.draw.drawCenteredText

@Composable
@OptIn(ExperimentalTextApi::class)
fun AvatarPlaceholder(fullName: String) {
    val text = remember { getAvatarPlaceholderText(fullName) }
    val textStyle = MaterialTheme.typography.subtitle1
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = remember { textMeasurer.measure(text, textStyle) }

    Canvas(
        modifier = Modifier.fillMaxSize(),
    ) {
        drawCircle(Color.Blue)
        drawCenteredText(
            textLayoutResult = textLayoutResult,
            color = Color.White,
        )
    }
}

private fun getAvatarPlaceholderText(fullName: String): String {
    val names = fullName.split(' ', limit = 2)
    val firstName = names[0]
    val lastName = names.getOrNull(1)
    val capitalFirstNameChar = firstName.first().uppercase()
    val capitalLastNameChar = lastName?.first()?.uppercase().orEmpty()
    return "$capitalFirstNameChar$capitalLastNameChar"
}
