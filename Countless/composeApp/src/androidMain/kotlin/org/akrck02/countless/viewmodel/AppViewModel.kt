package org.akrck02.countless.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.akrck02.countless.data.FinancialProcessor
import org.akrck02.countless.data.model.Account
import org.akrck02.countless.data.model.FinancialGoal
import org.akrck02.countless.data.repository.AccountRepository
import org.akrck02.countless.data.repository.FinancialGoalRepository

private const val DEFAULT_ACCOUNT_ID = 1

class AppViewModel(
    private val accountRepository: AccountRepository,
    private val financialGoalRepository: FinancialGoalRepository,
) : ViewModel() {

    var financialProcessor: FinancialProcessor = FinancialProcessor()

    var loaded: Boolean by mutableStateOf(false)
    var currentAccount: Account? = null
    var currentFinancialGoal: FinancialGoal? = null


    init {
        start()
    }

    fun isFirstTime(): Boolean = currentAccount == null

    private suspend fun loadAccountDataIfPresent() {

        // The account already loaded
        if (null != currentAccount) return

        // Find if the account exists, create otherwise.
        currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID)
        currentAccount?.also {
            currentFinancialGoal = financialGoalRepository.findByAccount(it.id!!).firstOrNull()
        }


    }

    fun start() {
        viewModelScope.launch {
            loadAccountDataIfPresent()
            financialProcessor.sync()
            loaded = true
        }
    }

    fun createFirstAccountAndGoal(account: Account, goal: FinancialGoal, onFinish: () -> Unit) {
        viewModelScope.launch {
            accountRepository.create(account)
            currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID) ?: throw IllegalStateException("Account not found just after creation.")

            currentAccount?.also {
                goal.accountId = it.id
                financialGoalRepository.create(goal)
                currentFinancialGoal = financialGoalRepository.findByAccount(it.id!!).firstOrNull()
                onFinish()
            }
        }
    }

    fun getBudgetDifference(): Double {

        currentFinancialGoal?.also {
            return it.currentValue - financialProcessor.estimatedBudgetForToday
        }

        return 0 - financialProcessor.estimatedBudgetForToday
    }

    fun getMonthBudgetExcess(): Double {

        currentFinancialGoal?.also {
            return it.currentValue - financialProcessor.estimatedBudgetForMonth
        }

        return 0.0
    }

    fun getEstimatedTime(): Long {
        return financialProcessor.estimatedTimestamp
    }
}