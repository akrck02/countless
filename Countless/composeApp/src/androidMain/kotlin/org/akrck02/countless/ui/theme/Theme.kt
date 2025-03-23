package org.akrck02.countless.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun getSystemThemeColors(): ColorScheme {
    return when {
        dynamicColor && isSystemInDarkTheme() -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !isSystemInDarkTheme() -> dynamicLightColorScheme(LocalContext.current)
        isSystemInDarkTheme() -> darkTheme
        else -> darkTheme
    }

}

val darkTheme = darkColorScheme(
    primary = Color(0xFFA1C698),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF958DA5),
    onSecondary = Color(0xFF9A8E75),

    surface = Color(0xFF5A5A5A),
    onSurface = Color(0xFF9A8E75),

    background = Color(15, 15, 15),
    onBackground = Color(155, 155, 155),

    error = Color.Red,
    onError = Color.White,
)

var bottomBar = Color(0xFF505050)

val DEFAULT_BOTTOM_BAR_BG = Color(18, 18, 18)

val DEFAULT_ROUNDED_SHAPE = RoundedCornerShape(20)
val MIN_ROUNDED_SHAPE = RoundedCornerShape(10.dp)
val TOTAL_ROUNDED_SHAPE = RoundedCornerShape(100)


val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S