package org.akrck02.countless.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.TOTAL_ROUNDED_SHAPE


@Composable
fun <T> TabBar(
    items: Map<String, T>,
    selected: T? = items.values.firstOrNull(),
    onSelected: (T) -> Unit
) {

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 50.dp)
    ) {
        items.keys.forEach { key ->

            val item = items[key] ?: return@forEach
            val selected = item == selected
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Surface(
                    color = if (selected) MaterialTheme.colorScheme.surfaceContainerHighest else MaterialTheme.colorScheme.surfaceContainer,
                    modifier = Modifier.padding(horizontal = 0.dp),
                    shape = TOTAL_ROUNDED_SHAPE,
                    onClick = { onSelected(item) }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(35.dp)

                    ) {
                        Text(
                            text = key,
                            fontSize = 5.em,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = if (selected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurfaceVariant.modify(
                                .5f
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(100.dp, 1000.dp)
                                .padding()
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun <T> MinimalTabBar(
    items: Map<String, T>,
    selected: T? = items.values.firstOrNull(),
    onSelected: (T) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 25.dp)
        ) {
            items.keys.forEach { key ->

                val item = items[key] ?: return@forEach
                val isSelected = item == selected
                Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Surface(
                        shape = TOTAL_ROUNDED_SHAPE,
                        color = _root_ide_package_.androidx.compose.ui.graphics.Color(0, 0, 0, 0),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            onSelected(item)
                        }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .height(35.dp)
                        ) {
                            Text(
                                text = key,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = if (isSelected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurfaceVariant.modify(
                                    .5f
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .widthIn(40.dp, 1000.dp)
                                    .padding()
                            )
                        }
                    }
                }
            }
        }
    }
}
