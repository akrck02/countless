package org.akrck02.countless

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.biometrics_subtitle
import countless.composeapp.generated.resources.biometrics_title
import org.akrck02.countless.system.biometric.Biometrics
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.akrck02.countless.ui.view.LockView
import org.akrck02.countless.viewmodel.AppViewModel
import org.akrck02.countless.viewmodel.TutorialView
import org.jetbrains.compose.resources.stringResource
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

        var justFinishedTutorial by remember { mutableStateOf(false) }
        if (appViewModel.isFirstTime() && justFinishedTutorial.not()) {
            TutorialView(appViewModel) {
                justFinishedTutorial = true
            }
            return@MaterialTheme
        }

        val context = LocalContext.current
        val biometricsCompatible = Biometrics.isCompatible(context)

        if (authenticated.not() && justFinishedTutorial.not()) {

            var biometricsTitle = stringResource(Res.string.biometrics_title)
            var bioMetricsSubtitle = stringResource(Res.string.biometrics_subtitle)

            LockView(biometricsCompatible) {

                if (biometricsCompatible.not()) {
                    authenticated = true
                    return@LockView
                }

                Biometrics.authenticate(
                    context = context,
                    title = biometricsTitle,
                    subtitle = bioMetricsSubtitle,
                    onError = { authenticated = false },
                    onSuccess = { authenticated = true }
                )
            }

            return@MaterialTheme
        }

        var loaded = appViewModel.loaded
        if (loaded.not()) {

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Loading")
                }
            }

            return@MaterialTheme
        }

        BottomNavigationBar(appViewModel)

    }
}