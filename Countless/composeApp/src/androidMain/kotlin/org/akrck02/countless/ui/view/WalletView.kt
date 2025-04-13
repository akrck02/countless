package org.akrck02.countless.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.income_title
import countless.composeapp.generated.resources.outcome_title
import kotlinx.coroutines.launch
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
import java.text.DateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun WalletView(
    appViewModel: AppViewModel,
    viewModel: WalletViewModel = koinViewModel()
) {

    viewModel.changeAccountId(appViewModel.currentAccount?.id ?: -1)
    viewModel.changeFinancialGoalId(appViewModel.financialState?.financialGoal?.id ?: -1)

    val transactions: List<FinancialTransaction> = viewModel.transactions
    val income = viewModel.income
    val outcome = viewModel.outcome
    val coroutineScope = rememberCoroutineScope()

    if (viewModel.showNewTransaction) {
        AddTransactionDialogue(
            onAcceptRequest = {
                coroutineScope.launch {
                    viewModel.addNewFinancialTransaction(it)
                    viewModel.hideNewTransactionPanel()
                    appViewModel.sync(1000)
                }
            },
            onDismissRequest = { viewModel.hideNewTransactionPanel() }
        )
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            MenuBar(
                onNewTransactionSelected = { viewModel.showNewTransactionPanel() },
                onOptionSelected = { viewModel.changeSelectedTransactionType(it) }
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
    onNewTransactionSelected: () -> Unit,
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
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shadowElevation = 20.dp,
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
                    modifier = Modifier.padding(start = 3.dp, end = 5.dp),
                    onClick = { onNewTransactionSelected() }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface.modify(.5f),
                        contentDescription = "Add",
                        imageVector = Icons.Rounded.Add
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
            // .padding(bottom = padding.calculateBottomPadding())
            .fillMaxSize()
    ) {
        item {

            val month = Calendar.getInstance()
                .getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: ""
            val year = Calendar.getInstance().weekYear
            val title = "$month $year"
            SectionTitle(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 20.dp)
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
            Spacer(Modifier.height(20.dp))
        }

        items(transactions) {
            TransactionCard(
                name = it.name ?: "",
                subLabel = it.timestamp.asDate(DateFormat.LONG),
                value = "${it.value}€",
                color = if (it.value < 0.0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        }

        item {
            Spacer(Modifier.height(100.dp))
        }

    }

}
