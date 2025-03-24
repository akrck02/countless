package org.akrck02.countless.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.MEDIUM_ROUNDED_SHAPE

@Composable
fun MinimalInfoCard(label: String, value: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = MEDIUM_ROUNDED_SHAPE,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        vertical = 20.dp,
                        horizontal = 15.dp
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = label,
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )

                Text(
                    text = value,
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
            }

        }
    }


}