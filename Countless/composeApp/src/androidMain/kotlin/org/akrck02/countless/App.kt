package org.akrck02.countless

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.countless.system.biometric.authenticateWithBiometrics
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.menu.BottomNavigationBar
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.akrck02.countless.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

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

            if (authenticated.not()) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Countless",
                        fontSize = 12.5.em,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )

                    Text(
                        text = "Please enter your fingerprint to access.",
                        fontSize = 4.em,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface.modify(.5f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 300.dp)
                    )

                    var context = LocalContext.current
                    Surface(
                        onClick = {
                            authenticateWithBiometrics(
                                context = context,
                                onError = { authenticated = false },
                                onSuccess = { authenticated = true }
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Fingerprint,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "",
                            modifier = Modifier.size(70.dp)
                        )
                    }

                    Text(
                        text = "Akrck02 - ${LocalDate.now().year}",
                        fontSize = 4.em,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface.modify(.5f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    )

                }


                return@Scaffold
            }

            BottomNavigationBar(appViewModel)

        }
    }
}