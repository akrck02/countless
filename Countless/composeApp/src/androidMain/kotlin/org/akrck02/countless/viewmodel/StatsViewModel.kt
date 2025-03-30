package org.akrck02.countless.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.FinancialGoal
import org.akrck02.countless.data.repository.AccountRepository
import org.akrck02.countless.data.repository.FinancialGoalRepository

class StatsViewModel(
    private val accountRepository: AccountRepository,
    private val goalRepository: FinancialGoalRepository
) : ViewModel() {
    var currentFinancialGoal by mutableStateOf(FinancialGoal().apply {
        targetValue = 65000.00
        currentValue = 35365.99
        monthSpendLimit = 100.00
        estimatedTimestamp = System.currentTimeMillis()
    })

    fun getBudgetDifference(): Double {
        return 5123.00
    }

    fun getMonthBudgetExcess(): Double {
        return currentFinancialGoal.monthSpendLimit
    }
}