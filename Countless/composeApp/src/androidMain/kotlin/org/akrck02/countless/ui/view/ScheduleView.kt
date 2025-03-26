package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import countless.composeapp.generated.resources.month_selector_option
import countless.composeapp.generated.resources.outcome_title
import countless.composeapp.generated.resources.schedule_title
import countless.composeapp.generated.resources.year_selector_option
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.model.data.FinancialTransaction
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.MinimalTabBar
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.ui.component.TransactionCard
import org.akrck02.countless.ui.options.TransactionType
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


enum class Period(val resource: StringResource) {
    Month(Res.string.month_selector_option),
    Year(Res.string.year_selector_option);

    suspend fun getName(): String {
        return getString(resource)
    }
}

@Composable
fun ScheduleView(
    navController: NavHostController,
    viewModel: ScheduleViewModel = koinViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                top = 10.dp,
                bottom = 110.dp,
            )
    ) {
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

        var selectedTransactionType by remember { mutableStateOf(TransactionType.All) }
        val options = mapOf(
            Pair(stringResource(TransactionType.All.resource), TransactionType.All),
            Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
            Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        ) {

            MinimalTabBar<TransactionType>(options, selectedTransactionType) { selectedTransactionType = it }
            ScheduledWallet(selectedTransactionType, viewModel, selectedPeriod)
        }

    }
}

@Composable
fun ScheduledWallet(selectedTransactionType: TransactionType, viewModel: ScheduleViewModel, selectedPeriod: Period) {
    when (selectedTransactionType) {
        TransactionType.All -> AllScheduleWallet(viewModel, selectedPeriod)
        TransactionType.Savings -> SavingsScheduleWallet(viewModel, selectedPeriod)
        TransactionType.Expenses -> ExpensesScheduleWallet(viewModel, selectedPeriod)
    }
}

@Composable
fun AllScheduleWallet(viewModel: ScheduleViewModel, selectedPeriod: Period) {

    for (i in 1..100) {
        TransactionCard("Spotify premium", "28/03/2025", "-14342.99€", MaterialTheme.colorScheme.error)
    }

    val transaction: List<FinancialTransaction> = remember { viewModel.getScheduledTransactions(selectedPeriod) }

    LazyColumn(modifier = Modifier.padding(top = 5.dp)) {

        items(transaction) { it ->
            TransactionCard(
                name = it.name ?: "",
                subLabel = it.timestamp.asDate(),
                value = "${it.value}€",
                color = MaterialTheme.colorScheme.error
            )
        }
    }


}

@Composable
fun SavingsScheduleWallet(viewModel: ScheduleViewModel, selectedPeriod: Period) {
    Column(modifier = Modifier.padding(top = 5.dp)) {
        Row {

        }
    }
}

@Composable
fun ExpensesScheduleWallet(viewModel: ScheduleViewModel, selectedPeriod: Period) {
    Column(modifier = Modifier.padding(top = 5.dp)) {
        Row {

        }
    }
}