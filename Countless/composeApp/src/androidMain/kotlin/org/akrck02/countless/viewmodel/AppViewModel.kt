package org.akrck02.countless.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.akrck02.countless.data.FinancialProcessor
import org.akrck02.countless.data.model.Account
import org.akrck02.countless.data.model.FinancialGoal
import org.akrck02.countless.data.model.FinancialState
import org.akrck02.countless.data.repository.AccountRepository
import org.akrck02.countless.data.repository.FinancialGoalRepository
import org.akrck02.countless.data.repository.TransactionRepository
import org.akrck02.countless.ui.navigation.Route
import org.akrck02.countless.ui.navigation.StatsRoute

private const val DEFAULT_ACCOUNT_ID = 1

class AppViewModel(
    private val accountRepository: AccountRepository,
    private val financialGoalRepository: FinancialGoalRepository,
    transactionRepository: TransactionRepository
) : ViewModel() {

    // Processors
    private val financialProcessor =
        FinancialProcessor(financialGoalRepository, transactionRepository)

    // App global state
    var currentRoute: Route by mutableStateOf(StatsRoute)
    var currentAccount: Account? = null
    var financialState: FinancialState? = null
    var accountLoaded: Boolean by mutableStateOf(false)
    var financialStateSynchronized: Boolean by mutableStateOf(false)

    /**
     * Load the account data on start
     */
    init {
        viewModelScope.launch {
            loadAccountDataIfPresent()
            accountLoaded = true
        }
    }

    /**
     * Get if is first time entering the app
     */
    fun isFirstTime(): Boolean = currentAccount == null

    /**
     * Synchronize necessary financial state to load the app
     */
    fun sync(delay: Long = 0) {
        viewModelScope.launch {
            financialStateSynchronized = false
            delay(delay)
            financialState?.also { financialProcessor.sync(it) }
            financialStateSynchronized = true
        }
    }

    /**
     * Load account data if an account is present
     */
    private suspend fun loadAccountDataIfPresent() {

        // The account already loaded
        if (null != currentAccount) return

        // Find if the account exists, create otherwise.
        currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID)
        currentAccount?.also {
            financialState = FinancialState().apply {
                financialGoal = financialGoalRepository.findByAccount(it.id!!).firstOrNull()
            }
        }
    }

    /**
     * Create first account and goal
     */
    fun createFirstAccountAndGoal(account: Account, goal: FinancialGoal, onFinish: () -> Unit) {
        viewModelScope.launch {
            accountRepository.create(account)
            currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID)
                ?: throw IllegalStateException("Account not found just after creation.")

            currentAccount?.also {
                goal.accountId = it.id
                financialGoalRepository.create(goal)
                financialState = FinancialState().apply {
                    financialGoal = financialGoalRepository.findByAccount(it.id!!).firstOrNull()
                }
                financialProcessor.sync(financialState!!)
                onFinish()
            }
        }
    }

    /**
     * Get the difference between estimated budget and current money
     */
    fun getBudgetDifference(): Double {

        financialState?.also { state ->
            state.financialGoal?.also {
                return it.currentValue - state.estimatedBudgetForToday
            }
        }

        return 0.0
    }

    /**
     * Get the difference between estimated budget for this month and current money
     */
    fun getMonthBudgetDifference(): Double {

        financialState?.also { state ->
            state.financialGoal?.also {
                return it.currentValue - state.estimatedBudgetForThisMonth
            }
        }

        return 0.0
    }

    /**
     * Get the estimated time to reach the current financial goal
     */
    fun getEstimatedTime(): Long {

        financialState?.also { state ->
            return state.financialGoal?.estimatedTimestamp ?: 0L
        }

        return 0L
    }
}