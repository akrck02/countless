package org.akrck02.countless.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.data.repository.TransactionRepository
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType
import kotlin.math.abs


class WalletViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    // region Variables
    private var accountId = -1
    private var financialGoalId = -1

    private var _showNewTransaction = false
    private var _selectedPeriod = Period.Month
    private var _income = 0.0
    private var _outcome = 0.0
    private var _selectedTransactionType = TransactionType.All
    private var _transactions = listOf<FinancialTransaction>()
    // endregion

    // region State
    var showNewTransaction by mutableStateOf(_showNewTransaction)
    var selectedPeriod by mutableStateOf(_selectedPeriod)
    var income by mutableDoubleStateOf(_income)
    var outcome by mutableDoubleStateOf(_outcome)
    var selectedTransactionType by mutableStateOf(_selectedTransactionType)
    var transactions by mutableStateOf(_transactions)
    // endregion

    //region methods
    fun changeAccountId(value: Int) {

        if (value == accountId) return

        accountId = value
    }

    fun changeFinancialGoalId(value: Int) {

        if (value == financialGoalId) return

        financialGoalId = value
        reloadValues(reloadIncomeAndOutcome = true, reloadTransactions = true)
    }

    fun showNewTransactionPanel() {
        showNewTransaction = true
    }

    fun hideNewTransactionPanel() {
        showNewTransaction = false
    }

    fun changeSelectedTransactionType(it: TransactionType) {
        selectedTransactionType = it
        reloadValues(reloadTransactions = true)
    }

    private fun reloadValues(
        reloadTransactions: Boolean = false,
        reloadIncomeAndOutcome: Boolean = false
    ) {

        if (accountId == -1 || financialGoalId == -1) return

        Log.d("TAG", "")
        Log.d("TAG", "selected transaction: $selectedTransactionType")
        Log.d("TAG", "show transaction panel: $showNewTransaction")
        Log.d("TAG", "selected period: $selectedPeriod")
        Log.d("TAG", "income: $income")
        Log.d("TAG", "outcome: $outcome")
        Log.d("TAG", "transactions: $transactions")
        Log.d("TAG", "")

        viewModelScope.launch {

            if (reloadTransactions) transactions = getTransactions()

            if (reloadIncomeAndOutcome) {
                var inc = 0.0
                var out = 0.0

                transactions.forEach {
                    if (it.value > 0) inc += it.value
                    else out += abs(it.value)
                }

                income = inc
                outcome = out
            }

        }

    }

    private suspend fun getTransactions(): List<FinancialTransaction> {

        return when (selectedTransactionType) {
            TransactionType.All -> transactionRepository.findAllByAccountId(accountId)
            TransactionType.Savings -> transactionRepository.findSavingsByAccountId(accountId)
            TransactionType.Expenses -> transactionRepository.findExpensesByAccountId(accountId)
        }
    }

    suspend fun addNewFinancialTransaction(financialTransaction: FinancialTransaction) {
        viewModelScope.launch {
            financialTransaction.accountId = accountId
            transactionRepository.create(financialTransaction)
        }.join()

    }

    // endregion
}