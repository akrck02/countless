package org.akrck02.countless.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.income_title
import countless.composeapp.generated.resources.outcome_title
import countless.composeapp.generated.resources.schedule_title_template
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.MinimalTabBar
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.ui.component.TransactionCard
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType
import org.akrck02.countless.viewmodel.AppViewModel
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleView(
    appViewModel: AppViewModel,
    viewModel: ScheduleViewModel = koinViewModel()
) {

    var selectedTransactionType by remember { mutableStateOf(TransactionType.All) }
    val options = mapOf(
        Pair(stringResource(TransactionType.All.resource), TransactionType.All),
        Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
        Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
    )
    var selectedPeriod by remember { mutableStateOf(Period.Month) }

    var transactions: List<FinancialTransaction> by remember { mutableStateOf(listOf()) }
    LazyColumn(
        userScrollEnabled = true,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxSize()
    ) {
        item {
            SectionTitle(
                text = stringResource(Res.string.schedule_title_template, stringResource(selectedPeriod.resource)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 30.dp)
            ) {
                selectedPeriod = if (selectedPeriod == Period.Month) Period.Year else Period.Month
            }

            var income = if (selectedPeriod == Period.Month) appViewModel.financialProcessor.monthIncome else appViewModel.financialProcessor.yearIncome
            var outcome = if (selectedPeriod == Period.Month) appViewModel.financialProcessor.monthOutcome else appViewModel.financialProcessor.yearOutcome
            Row {
                MinimalInfoCard(stringResource(Res.string.income_title), "${income.defaultDigitFormat()}€")
                MinimalInfoCard(stringResource(Res.string.outcome_title), "${outcome.defaultDigitFormat()}€")
            }

            MinimalTabBar(options, selectedTransactionType) {
                selectedTransactionType = it
                transactions =
                    viewModel.getScheduledTransactions(appViewModel.currentAccount?.id ?: 1, selectedTransactionType, selectedPeriod)
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


