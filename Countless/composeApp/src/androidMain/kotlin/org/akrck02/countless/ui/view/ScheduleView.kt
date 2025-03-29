package org.akrck02.countless.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.NavHostController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.income_title
import countless.composeapp.generated.resources.outcome_title
import countless.composeapp.generated.resources.schedule_title
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.model.data.FinancialTransaction
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.MinimalTabBar
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.ui.component.TransactionCard
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleView(
    navController: NavHostController,
    viewModel: ScheduleViewModel = koinViewModel()
) {

    var selectedTransactionType by remember { mutableStateOf(TransactionType.All) }
    val options = mapOf(
        Pair(stringResource(TransactionType.All.resource), TransactionType.All),
        Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
        Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
    )

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
                text = stringResource(Res.string.schedule_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, bottom = 10.dp)
            )

            Row(horizontalArrangement = Arrangement.Center) {
                MinimalInfoCard(stringResource(Res.string.income_title), "30,970€")
                MinimalInfoCard(stringResource(Res.string.outcome_title), "11,207.99€")
            }

            var selectedPeriod by remember { mutableStateOf(Period.Month) }
            MinimalTabBar(options, selectedTransactionType) {
                selectedTransactionType = it
                transactions =
                    viewModel.getScheduledTransactions(selectedTransactionType, selectedPeriod)
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


