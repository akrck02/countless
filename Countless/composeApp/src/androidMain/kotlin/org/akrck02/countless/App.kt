package org.akrck02.countless

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.biometrics_subtitle
import countless.composeapp.generated.resources.biometrics_title
import org.akrck02.countless.system.biometric.Biometrics
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.akrck02.countless.ui.view.LockView
import org.akrck02.countless.ui.view.StartLoadingView
import org.akrck02.countless.ui.view.tutorial.TutorialView
import org.akrck02.countless.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@RequiresApi(Build.VERSION_CODES.R)
@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {
    KoinContext {
        MaterialTheme(
            colorScheme = getSystemThemeColors(),
            shapes = MaterialTheme.shapes
        ) {

            // Load account and show loading screen
            if (appViewModel.accountLoaded.not()) {
                // StartLoadingView()
                return@MaterialTheme
            }

            // Show tutorial if necessary
            var justFinishedTutorial by remember { mutableStateOf(false) }
            if (appViewModel.isFirstTime() && justFinishedTutorial.not()) {
                TutorialView(appViewModel) { justFinishedTutorial = true }
                return@MaterialTheme
            }

            // If tutorial just finished, lead to app
            if (justFinishedTutorial) {
                BottomNavigationBar(appViewModel)
                return@MaterialTheme
            }

            // If Not authenticated, show lock screen
            var authenticated by remember { mutableStateOf(false) }
            if (authenticated.not()) {

                val context = LocalContext.current
                val biometricsCompatible = Biometrics.isCompatible(context)
                val biometricsTitle = stringResource(Res.string.biometrics_title)
                val bioMetricsSubtitle = stringResource(Res.string.biometrics_subtitle)

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

            // if synchronization needed, sync data and show loading screen
            if (appViewModel.financialStateSynchronized.not()) {
                StartLoadingView()
                appViewModel.sync(1000)
                return@MaterialTheme
            }


            // Show app
            BottomNavigationBar(appViewModel)
        }
    }
}

