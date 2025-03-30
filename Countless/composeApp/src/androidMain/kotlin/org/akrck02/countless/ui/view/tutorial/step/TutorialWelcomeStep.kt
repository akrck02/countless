package org.akrck02.countless.ui.view.tutorial.step

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.tutorial_greeting
import countless.composeapp.generated.resources.tutorial_start_account_creation
import org.akrck02.countless.ui.extension.modify
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun WelcomeStep() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.tutorial_greeting),
            fontSize = 27.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = stringResource(Res.string.tutorial_start_account_creation),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
        )
    }
}