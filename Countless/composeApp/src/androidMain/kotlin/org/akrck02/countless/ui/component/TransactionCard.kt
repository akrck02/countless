package org.akrck02.countless.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.MEDIUM_ROUNDED_SHAPE

@Composable
fun TransactionCard(
    name: String = "Name",
    subLabel: String = "Sub label",
    value: String = "0.00€",
    color: Color = MaterialTheme.colorScheme.primary
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 20.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = MEDIUM_ROUNDED_SHAPE,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 90.dp, max = 1000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(.60f)
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.8f)
                    )
                    Text(
                        text = subLabel,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .heightIn(min = 50.dp, max = 1000.dp)
                        .weight(.4f)
                ) {
                    Text(
                        text = value,
                        color = color,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}