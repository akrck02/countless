package org.akrck02.countless.ui.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.akrck02.countless.ui.extension.modify

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick
        )
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface.modify(.6f),
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }


}