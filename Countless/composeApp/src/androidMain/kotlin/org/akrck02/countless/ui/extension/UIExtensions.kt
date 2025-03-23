package org.akrck02.countless.ui.extension

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun Color.makeLighter(factor: Float = 0.3f): Color {
    return copy(
        red = red + red * factor,
        green = green + red * factor,
        blue = blue + red * factor,
        alpha = alpha
    )
}

fun Color.makeDarker(factor: Float = 0.3f): Color {
    return copy(
        red = red * factor,
        green = green * factor,
        blue = blue * factor,
        alpha = alpha
    )
}

@Composable
fun Color.modify(factor: Float = 0.3f): Color {
    return if (isSystemInDarkTheme()) this.makeDarker(factor) else this.makeLighter(factor)
}