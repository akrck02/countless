package org.akrck02.countless.ui.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.akrck02.countless.data.extension.asDate
import java.text.DateFormat

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MaterialDatePicker(
    label: String = "label",
    dialogAcceptLabel: String = "OK",
    dialogDismissLabel: String = "Cancel",
    onDismiss: () -> Unit = {},
    onDateSelected: (Long?) -> Unit = {}
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis() + 7889238000)
    var focus by remember { mutableStateOf(false) }
    var value by remember { mutableLongStateOf(datePickerState.selectedDateMillis!!) }

    DisableSoftKeyboard {
        MaterialTextField(
            label = label,
            onClick = { focus = true },
            value = value.asDate(DateFormat.LONG)
        )
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