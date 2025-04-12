package org.akrck02.countless.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.data.repository.ScheduleRepository
import org.akrck02.countless.data.repository.TransactionRepository
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType


class WalletViewModel(
    private val transactionRepository: TransactionRepository,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    // Variables
    private var _scheduleMode = mutableStateOf(false)
    private var _showNewTransaction = mutableStateOf(false)
    private var _selectedPeriod = mutableStateOf(Period.Month)
    private var _income = mutableDoubleStateOf(0.0)
    private var _outcome = mutableDoubleStateOf(0.0)
    private var _selectedTransactionType = mutableStateOf(TransactionType.All)
    private var _transactions = mutableStateOf(listOf<FinancialTransaction>())

    // State
    val scheduleMode: State<Boolean> get() = _scheduleMode
    val showNewTransaction: State<Boolean> get() = _showNewTransaction
    val selectedPeriod: State<Period> get() = _selectedPeriod
    val income: State<Double> get() = _income
    val outcome: State<Double> get() = _outcome
    val selectedTransactionType: State<TransactionType> get() = _selectedTransactionType
    val transactions: State<List<FinancialTransaction>> get() = _transactions


    fun showNewTransactionPanel() {
        _showNewTransaction.value = true
    }

    fun hideNewTransactionPanel() {
        _showNewTransaction.value = false
    }

    fun toggleScheduleMode() {
        _scheduleMode.value = !_scheduleMode.value
        reloadValues()
    }

    fun setSelectedTransactionType(it: TransactionType) {
        _selectedTransactionType.value = it
        reloadValues()
    }

    private fun reloadValues() {
        Log.d("TAG", "")
        Log.d("TAG", "selected transaction: ${_selectedTransactionType.value}")
        Log.d("TAG", "schedule mode: ${_scheduleMode.value}")
        Log.d("TAG", "show transaction panel: ${_showNewTransaction.value}")
        Log.d("TAG", "selected period: ${_selectedPeriod.value}")
        Log.d("TAG", "income: ${_income.doubleValue}")
        Log.d("TAG", "outcome: ${_outcome.doubleValue}")
        Log.d("TAG", "transactions: ${_transactions.value}")
        Log.d("TAG", "")
    }

    fun getTransactions(
        accountId: Int,
        selectedTransactionType: TransactionType,
        period: Period,
        scheduled: Boolean = false
    ): List<FinancialTransaction> {
        return listOf()
    }


}