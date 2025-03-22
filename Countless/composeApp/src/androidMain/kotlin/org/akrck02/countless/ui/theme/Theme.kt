package org.akrck02.countless.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun getSystemThemeColors(): Colors {
    return Colors(
        primary = Color(0xFFA1C698),
        onPrimary = Color(0xFFFFFFFF),

        secondary = Color(0xFF958DA5),
        onSecondary = Color(0xFF9A8E75),

        surface = Color(0xFF5A5A5A),
        onSurface = Color(0xFF9A8E75),

        background = Color(15, 15, 15),
        onBackground = Color(0xFF9A8E75),

        error = Color.Red,
        onError = Color.White,
        primaryVariant = Color.White,
        secondaryVariant = Color.White,
        isLight = false,
    )
}

val DEFAULT_BOTTOM_BAR_BG = Color(18, 18, 18)

val DEFAULT_ROUNDED_SHAPE = RoundedCornerShape(20)
val MIN_ROUNDED_SHAPE = RoundedCornerShape(10.dp)
val TOTAL_ROUNDED_SHAPE = RoundedCornerShape(100)