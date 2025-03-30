package org.akrck02.countless.ui.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.ui.theme.MIN_ROUNDED_SHAPE
import java.text.DateFormat

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MaterialDatePicker(
    label: String = "label",
    dialogAcceptLabel: String = "OK",
    dialogDismissLabel: String = "Cancel",
    width: Dp = 270.dp,
    value: Long = System.currentTimeMillis(),
    onDismiss: () -> Unit = {},
    onDateSelected: (Long?) -> Unit = {},
    icon: ImageVector,
    isError: Boolean = false
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = value)
    var focus by remember { mutableStateOf(false) }
    var value by remember { mutableLongStateOf(datePickerState.selectedDateMillis!!) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MIN_ROUNDED_SHAPE,
        modifier = Modifier
            .padding(10.dp)
            .focusable(
                enabled = true,
                interactionSource = MutableInteractionSource()
            ),
        onClick = { focus = true },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 15.dp)
                .height(50.dp)
                .width(width),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 0.dp)
                        .height(20.dp)
                )
                Text(
                    text = value.asDate(DateFormat.LONG),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp
                )
            }

            Column {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }



    if (focus) {
        DatePickerDialog(
            onDismissRequest = { focus = false },
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    value = datePickerState.selectedDateMillis ?: 0
                    focus = false
                    onDismiss()
                }) { Text(dialogAcceptLabel) }
            },
            dismissButton = {
                TextButton(onClick = {
                    focus = false
                    onDismiss()
                }) { Text(dialogDismissLabel) }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}