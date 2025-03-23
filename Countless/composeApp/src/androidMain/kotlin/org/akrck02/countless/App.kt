package org.akrck02.countless

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.akrck02.countless.system.biometric.AuthenticateWithBiometrics
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.akrck02.countless.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {

    var authenticated by remember { mutableStateOf(false) }
    AuthenticateWithBiometrics(
        onError = { authenticated = false },
        onSuccess = { authenticated = true }
    )

    MaterialTheme(
        colorScheme = getSystemThemeColors(),
        shapes = MaterialTheme.shapes
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface
        ) { contentPadding ->

            contentPadding.toString()

            if (authenticated.not()) {
                AuthenticateWithBiometrics(
                    onError = { authenticated = false },
                    onSuccess = { authenticated = true }
                )
                return@Scaffold
            }

            BottomNavigationBar(appViewModel)

        }
    }
}