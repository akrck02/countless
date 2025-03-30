package org.akrck02.countless.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.app_author_template
import countless.composeapp.generated.resources.app_name
import countless.composeapp.generated.resources.app_subtitle
import org.akrck02.countless.ui.extension.modify
import org.jetbrains.compose.resources.stringResource
import java.time.LocalDate

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun LockView(
    useBiometricIcon: Boolean,
    onUnlockClick: () -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(Res.string.app_name),
                fontSize = 36.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            Text(
                text = stringResource(Res.string.app_subtitle),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.modify(.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 300.dp)
            )

            Surface(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) { onUnlockClick() }
            ) {
                Icon(
                    imageVector = if (useBiometricIcon) Icons.Rounded.Fingerprint else Icons.Rounded.LockOpen,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "",
                    modifier = Modifier.size(70.dp)
                )
            }

            Text(
                text = stringResource(Res.string.app_author_template, LocalDate.now().year),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.modify(.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )
        }
    }

}