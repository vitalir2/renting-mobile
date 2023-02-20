package com.renting.app.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val darkPrimary = Color(0xFFBB86FC)
private val darkPrimaryVariant = Color(0xFF3700B3)
private val darkSecondary = Color(0xFF03DAC5)
private val lightPrimary = Color(0xFF6200EE)
private val lightPrimaryVariant = Color(0xFF3700B3)
private val lightSecondary = Color(0xFF03DAC5)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = darkPrimary,
            primaryVariant = darkPrimaryVariant,
            secondary = darkSecondary
        )
    } else {
        lightColors(
            primary = lightPrimary,
            primaryVariant = lightPrimaryVariant,
            secondary = lightSecondary
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
