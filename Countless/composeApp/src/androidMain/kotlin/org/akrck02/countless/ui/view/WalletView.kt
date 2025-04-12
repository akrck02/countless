package org.akrck02.countless.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.income_title
import countless.composeapp.generated.resources.outcome_title
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.ui.component.TabBar
import org.akrck02.countless.ui.component.TransactionCard
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.menu.AddTransactionDialogue
import org.akrck02.countless.ui.options.TransactionType
import org.akrck02.countless.viewmodel.AppViewModel
import org.akrck02.countless.viewmodel.WalletViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar
import java.util.Locale


@Composable
fun WalletView(
    appViewModel: AppViewModel,
    viewModel: WalletViewModel = koinViewModel()
) {

    val scheduleMode by remember { viewModel.scheduleMode }
    val showNewTransaction by remember { viewModel.showNewTransaction }
    val selectedTransactionType by remember { viewModel.selectedTransactionType }
    val selectedPeriod by remember { viewModel.selectedPeriod }
    val transactions: List<FinancialTransaction> by remember { viewModel.transactions }
    val income by remember { viewModel.income }
    val outcome by remember { viewModel.outcome }

    if (showNewTransaction) {
        AddTransactionDialogue(scheduleMode) { viewModel.hideNewTransactionPanel() }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            MenuBar(
                scheduleMode,
                onNewTransactionSelected = { viewModel.showNewTransactionPanel() },
                onScheduledSelected = { viewModel.toggleScheduleMode() },
                onOptionSelected = { viewModel.setSelectedTransactionType(it) }
            )
        }
    ) {
        Payments(
            viewModel,
            it,
            income,
            outcome,
            transactions
        )
    }
}

@Composable
private fun MenuBar(
    scheduledSelected: Boolean,
    onNewTransactionSelected: () -> Unit,
    onScheduledSelected: () -> Unit,
    onOptionSelected: (TransactionType) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)

    ) {
        Surface(
            shape = RoundedCornerShape(100),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            modifier = Modifier.widthIn(0.dp, 380.dp)
        ) {
            var selected by remember { mutableStateOf(TransactionType.All) }
            val options = mapOf(
                Pair(stringResource(TransactionType.All.resource), TransactionType.All),
                Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
                Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TabBar(options, selected) {
                    selected = it
                    onOptionSelected(it)
                }
                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(100),
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    onClick = { onNewTransactionSelected() }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface.modify(.5f),
                        contentDescription = "Add",
                        imageVector = Icons.Rounded.Add
                    )
                }

                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(100),
                    modifier = Modifier
                        .padding(start = 5.dp, end = 15.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { onScheduledSelected() }
                        )
                ) {
                    Icon(
                        tint = if (scheduledSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.modify(
                            .5f
                        ),
                        contentDescription = "History",
                        imageVector = Icons.Rounded.History
                    )
                }
            }
        }
    }
}

@Composable
fun Payments(
    viewModel: WalletViewModel,
    padding: PaddingValues,
    income: Double,
    outcome: Double,
    transactions: List<FinancialTransaction>
) {

    LazyColumn(
        userScrollEnabled = true,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
    ) {
        item {
            SectionTitle(
                text = Calendar.getInstance()
                    .getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 30.dp)
            )

            Row {
                MinimalInfoCard(
                    stringResource(Res.string.income_title),
                    "${income.defaultDigitFormat()}€"
                )
                MinimalInfoCard(
                    stringResource(Res.string.outcome_title),
                    "${outcome.defaultDigitFormat()}€"
                )
            }
        }

        items(transactions) {
            TransactionCard(
                name = it.name ?: "",
                subLabel = it.timestamp.asDate(),
                value = "${it.value}€",
                color = MaterialTheme.colorScheme.error
            )
        }

    }

}
