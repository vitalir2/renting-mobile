package com.renting.app.android.feature.home

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
import com.renting.app.core.auth.model.UserInfo

@Composable
@OptIn(ExperimentalTextApi::class)
fun AvatarPlaceholder(
    userInfo: UserInfo,
) {
    val text = remember { userInfo.avatarPlaceholderText }
    val textStyle = MaterialTheme.typography.subtitle1
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = remember {
        textMeasurer.measure(
            text = text,
            style = textStyle,
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        drawCircle(Color.Blue)
        drawCenteredText(
            textLayoutResult = textLayoutResult,
            color = Color.White,
        )
    }
}

private val UserInfo.avatarPlaceholderText: String
    get() {
        val capitalFirstNameChar = firstName.first().uppercase()
        val capitalLastNameChar = lastName.first().uppercase()
        return "$capitalFirstNameChar$capitalLastNameChar"
    }
