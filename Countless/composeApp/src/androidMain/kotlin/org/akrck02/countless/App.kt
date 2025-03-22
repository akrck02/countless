package org.akrck02.countless

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = getSystemThemeColors(),
        shapes = MaterialTheme.shapes
    ) {
        Scaffold { _ ->

            BottomNavigationBar()
        }


    }
}