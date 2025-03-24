package org.akrck02.countless.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.countless.ui.extension.modify

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(top = 60.dp, bottom = 40.dp)
) {

    Text(
        text = text,
        fontSize = 8.em,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface.modify(.6f),
        textAlign = TextAlign.Center,
        modifier = modifier
    )

}