package org.akrck02.countless.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.MEDIUM_ROUNDED_SHAPE


@Composable
fun AddMenuBar() {

    var showNewTransaction by remember { mutableStateOf(false) }
    var showNewPlan by remember { mutableStateOf(false) }

    if (showNewPlan.not().and(showNewTransaction.not()))
        AddMenu(
            onTransactionClick = { showNewTransaction = true },
            onPlanClick = { showNewPlan = true }
        )

    if (showNewTransaction) AddTransactionDialgue { showNewTransaction = false }
    if (showNewPlan) AddPlanDialogue { showNewPlan = false }
}

@Composable
fun AddMenu(
    onTransactionClick: () -> Unit,
    onPlanClick: () -> Unit
) {
    Surface(
        shape = MEDIUM_ROUNDED_SHAPE,
        color = Color.Transparent, //MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(0.dp)
                .offset(x = 0.dp)
        ) {

            AddMenuOption(
                label = "transacción",
                icon = Icons.Rounded.Payments,
                onClick = onTransactionClick
            )
            AddMenuOption(
                label = "plan",
                icon = Icons.Rounded.Autorenew,
                onClick = onPlanClick
            )
        }

    }

}


@Composable
private fun AddMenuOption(
    label: String = "label",
    icon: ImageVector = Icons.Rounded.Add,
    onClick: () -> Unit
) {
    Surface(
        shape = MEDIUM_ROUNDED_SHAPE,
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(85.dp)
            .width(110.dp)
        //  .border(2.dp, MaterialTheme.colorScheme.onSurface.modify(.4f), MEDIUM_ROUNDED_SHAPE)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {

            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.onSurface.modify(.8f),
                contentDescription = null
            )
            Text(
                text = label,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 5.dp),
                color = MaterialTheme.colorScheme.onSurface.modify(.8f)
            )

        }
    }
}