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
import androidx.compose.ui.platform.LocalContext
import org.akrck02.countless.system.biometric.authenticateWithBiometrics
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.akrck02.countless.ui.view.LockView
import org.akrck02.countless.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {

    var authenticated by remember { mutableStateOf(false) }
    MaterialTheme(
        colorScheme = getSystemThemeColors(),
        shapes = MaterialTheme.shapes
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface
        ) { contentPadding ->

            contentPadding.toString()

            var context = LocalContext.current
            if (authenticated.not()) {
                LockView {
                    authenticateWithBiometrics(
                        context = context,
                        onError = { authenticated = false },
                        onSuccess = { authenticated = true }
                    )
                }

                return@Scaffold
            }

            BottomNavigationBar(appViewModel)

        }
    }
}