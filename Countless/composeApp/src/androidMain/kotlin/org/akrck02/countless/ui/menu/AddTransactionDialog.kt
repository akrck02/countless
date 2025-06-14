package org.akrck02.countless.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.EuroSymbol
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.ui.component.MaterialDatePicker
import org.akrck02.countless.ui.component.MaterialTextField

@Composable
fun AddTransactionDialogue(
    onAcceptRequest: (FinancialTransaction) -> Unit,
    onDismissRequest: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var date by remember { mutableLongStateOf(System.currentTimeMillis()) }

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = onDismissRequest
    ) {
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 40.dp,
                    start = 40.dp,
                    end = 40.dp
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 30.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "New transaction",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                MaterialTextField(
                    label = "Name",
                    value = name,
                    icon = Icons.Rounded.Edit,
                    onValueChange = { name = it }
                )

                MaterialTextField(
                    label = "Value",
                    value = value,
                    icon = Icons.Rounded.EuroSymbol,
                    type = KeyboardType.Number,
                    onValueChange = { value = it }
                )

                MaterialDatePicker(
                    label = "Date",
                    value = date,
                    onDateSelected = { date = it ?: 0L }
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        onAcceptRequest(
                            FinancialTransaction(
                                name = name,
                                timestamp = date,
                                value = value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }) { Text("Accept") }
                    Spacer(Modifier.width(15.dp))
                    Button(
                        onClick = onDismissRequest, colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            disabledContentColor = MaterialTheme.colorScheme.surfaceContainer,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        )
                    ) { Text("Cancel") }
                }

            }

        }
    }
}