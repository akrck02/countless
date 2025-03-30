package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.MinimalTabBar
import org.akrck02.countless.ui.component.SectionTitle
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
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                top = 10.dp,
                bottom = 110.dp,
                start = 0.dp,
                end = 0.dp
            )
    ) {
        SectionTitle(
            text = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 30.dp)
        )

        var income = appViewModel.financialProcessor.monthIncome
        var outcome = appViewModel.financialProcessor.monthOutcome
        Row {
            MinimalInfoCard(stringResource(Res.string.income_title), "${income.defaultDigitFormat()}€")
            MinimalInfoCard(stringResource(Res.string.outcome_title), "${outcome.defaultDigitFormat()}€")
        }

        var selected by remember { mutableStateOf(TransactionType.All) }
        val options = mapOf(
            Pair(stringResource(TransactionType.All.resource), TransactionType.All),
            Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
            Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
        )

        MinimalTabBar(options, selected) { selected = it }
        when (selected) {
            TransactionType.All -> AllWallet()
            TransactionType.Savings -> SavingsWallet()
            TransactionType.Expenses -> ExpensesWallet()
        }
    }
}

@Composable
fun AllWallet() {
    Column(modifier = Modifier.padding(top = 35.dp)) {
        Row {

        }
    }
}

@Composable
fun SavingsWallet() {
    Column(modifier = Modifier.padding(top = 35.dp)) {
        Row {

        }
    }
}

@Composable
fun ExpensesWallet() {
    Column(modifier = Modifier.padding(top = 35.dp)) {
        Row {

        }
    }
}