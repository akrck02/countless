package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.tutorial_preparing_app_subtitle
import countless.composeapp.generated.resources.tutorial_preparing_app_title
import org.akrck02.countless.ui.extension.modify
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun StartLoadingView() {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(Res.string.tutorial_preparing_app_title),
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Text(
                text = stringResource(Res.string.tutorial_preparing_app_subtitle),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            LoadingIndicator()
        }
    }
}