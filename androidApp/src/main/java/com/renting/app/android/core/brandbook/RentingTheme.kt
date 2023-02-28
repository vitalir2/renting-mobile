package com.renting.app.android.core.brandbook

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

private object LightThemeColor {
    val primary = Color(0xFF246BFD)
    val primaryVariant = Color(0xFF264ABD)
    val secondary = Color(0xFFE4ECFF)
    val textSecondary = Color(0xFF616161)
    val background = Color(0xFFFFFFFF)
    val backgroundSecondary = Color(0xFFF5F5F5)
    val onPrimary = Color(0xFFFFFFFF)
    val onSecondary = primary
    val onBackground = Color(0xFF212121)
    val onBackgroundSecondary = Color(0xFF212121)
}

private object DarkThemeColor {
    val primary = Color(0xFF246BFD)
    val primaryVariant = Color(0xFF264ABD)
    val secondary = Color(0xFF282A30)
    val textSecondary = Color(0xFFE0E0E0)
    val background = Color(0xFF181A20)
    val backgroundSecondary = Color(0xFF1F222A)
    val onPrimary = Color(0xFFFFFFFF)
    val onSecondary = Color(0xFFFFFFFF)
    val onBackground = Color(0xFFFFFFFF)
    val onBackgroundSecondary = Color(0xFFFFFFFF)
}

@Composable
fun RentingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = DarkThemeColor.primary,
            primaryVariant = DarkThemeColor.primaryVariant,
            secondary = DarkThemeColor.secondary,
            secondaryVariant = DarkThemeColor.textSecondary,
            background = DarkThemeColor.background,
            surface = DarkThemeColor.backgroundSecondary,
            onPrimary = DarkThemeColor.onPrimary,
            onSecondary = DarkThemeColor.onSecondary,
            onBackground = DarkThemeColor.onBackground,
            onSurface = DarkThemeColor.onBackgroundSecondary,
        )
    } else {
        lightColors(
            primary = LightThemeColor.primary,
            primaryVariant = LightThemeColor.primaryVariant,
            secondary = LightThemeColor.secondary,
            secondaryVariant = LightThemeColor.textSecondary,
            background = LightThemeColor.background,
            surface = LightThemeColor.backgroundSecondary,
            onPrimary = LightThemeColor.onPrimary,
            onSecondary = LightThemeColor.onSecondary,
            onBackground = LightThemeColor.onBackground,
            onSurface = LightThemeColor.onBackgroundSecondary,
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
