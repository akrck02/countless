package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.wallet_transaction_type_all
import countless.composeapp.generated.resources.wallet_transaction_type_expenses
import countless.composeapp.generated.resources.wallet_transaction_type_savings
import org.akrck02.countless.ui.component.MinimalTabBar
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.viewmodel.WalletViewModel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel

enum class TransactionType(val resource: StringResource) {
    All(Res.string.wallet_transaction_type_all),
    Savings(Res.string.wallet_transaction_type_savings),
    Expenses(Res.string.wallet_transaction_type_expenses);

    suspend fun getName(): String {
        return getString(resource)
    }
}


@Composable
fun WalletView(
    navController: NavHostController,
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
        SectionTitle("March 2025")
        var selected by remember { mutableStateOf(TransactionType.All) }
        val options = mapOf(
            Pair(stringResource(TransactionType.All.resource), TransactionType.All),
            Pair(stringResource(TransactionType.Savings.resource), TransactionType.Savings),
            Pair(stringResource(TransactionType.Expenses.resource), TransactionType.Expenses)
        )

        MinimalTabBar<TransactionType>(options, selected) { selected = it }
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