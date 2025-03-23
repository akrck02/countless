package org.akrck02.countless

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.akrck02.countless.system.biometric.AuthenticateWithBiometrics
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@RequiresApi(Build.VERSION_CODES.R)
@Composable
@Preview
fun App() {

    var authenticated by remember { mutableStateOf(false) }
    AuthenticateWithBiometrics(
        onError = { authenticated = false },
        onSuccess = { authenticated = true }
    )

    MaterialTheme(
        colors = getSystemThemeColors(),
        shapes = MaterialTheme.shapes
    ) {
        Scaffold { contentPadding ->

            contentPadding.toString()

            if (authenticated.not()) {
                AuthenticateWithBiometrics(
                    onError = { authenticated = false },
                    onSuccess = { authenticated = true }
                )
                return@Scaffold
            }

            BottomNavigationBar()

        }
    }
}