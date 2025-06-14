package org.akrck02.countless.data


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.akrck02.countless.data.extension.daysBetween
import org.akrck02.countless.data.extension.endOfMonth
import org.akrck02.countless.data.extension.toLocalDateTime
import org.akrck02.countless.data.model.FinancialGoal
import org.akrck02.countless.data.model.FinancialState
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.data.repository.FinancialGoalRepository
import org.akrck02.countless.data.repository.TransactionRepository
import java.sql.Timestamp
import java.time.LocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class FinancialProcessor(
    private val financialGoalRepository: FinancialGoalRepository,
    private val transactionRepository: TransactionRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun sync(financialState: FinancialState) {

        financialState.financialGoal ?: return

        val savings = transactionRepository.findSavingsByAccountId(financialState.financialGoal!!.accountId!!)
        val expenses = transactionRepository.findExpensesByAccountId(financialState.financialGoal!!.accountId!!)

        val targetDateTime = financialState.financialGoal?.targetTimestamp?.toLocalDateTime() ?: return
        val insertDateTime = financialState.financialGoal?.insertTimestamp?.toLocalDateTime() ?: return

        val daysUntilTargetDate = max(insertDateTime.daysBetween(targetDateTime), 0)
        val perDayTargetSavings = financialState.financialGoal!!.targetValue / daysUntilTargetDate

        val daysUntilToday = max(insertDateTime.daysBetween(LocalDateTime.now()), 0)
        val daysUntilMonthFinish = max(insertDateTime.daysBetween(LocalDateTime.now().endOfMonth()), 0)

        Log.d("TAG", "days until target date: $daysUntilTargetDate")
        Log.d("TAG", "days until today: $daysUntilToday")
        Log.d("TAG", "days until month finish: $daysUntilMonthFinish")
        Log.d("TAG", "per day savings: $perDayTargetSavings")

        closeScheduledTransactions(financialState)
        syncCurrentValue(financialState, savings, expenses)
        syncEstimatedTimes(financialState)
        syncEstimatedBudget(financialState, perDayTargetSavings, daysUntilToday, daysUntilMonthFinish)
    }

    /**
     * Generate scheduled transactions until today
     */
    private fun closeScheduledTransactions(financialState: FinancialState) {

    }

    /**
     * Calculate and sync current financial goal value
     */
    private fun syncCurrentValue(financialState: FinancialState, savings: List<FinancialTransaction>, expenses: List<FinancialTransaction>) {
        financialState.financialGoal?.apply {
            currentValue = savings.sumOf { it.value } - expenses.sumOf { abs(it.value) }
        }
    }

    /**
     * Calculate and synchronize the estimated times
     */
    private fun syncEstimatedTimes(financialState: FinancialState) {

    }

    /**
     * Calculate and synchronize the estimated budget
     */
    private fun syncEstimatedBudget(financialState: FinancialState, perDayTargetSavings: Double, daysUntilToday: Long, daysUntilMonthFinish: Long) {

        financialState.estimatedBudgetForToday = min(daysUntilToday * perDayTargetSavings, financialState.financialGoal?.targetValue ?: 0.0)
        financialState.estimatedBudgetForThisMonth = min(daysUntilMonthFinish * perDayTargetSavings, financialState.financialGoal?.targetValue ?: 0.0)

        Log.d("TAG", "syncEstimatedBudget: ")
        Log.d("TAG", "syncEstimatedBudget: day ${financialState.estimatedBudgetForToday}")
        Log.d("TAG", "syncEstimatedBudget: month ${financialState.estimatedBudgetForThisMonth}")

    }

    /**
     * Get the budget target for a given timestamp
     */
    private fun targetForDay(financialGoal: FinancialGoal, timestamp: Long): Double {

        // 1. Get all the days between goal start and goal end

        // 2. Calculate the savings per day to be made

        // 3. Add until current day

        return 0.0
    }

    /**
     * Get the budget difference for a given timestamp
     */
    private fun getBudgetDifferenceForTime(financialGoal: FinancialGoal, timestamp: Long): Double {

        // 1. Get the target for the given day

        // 2. Get the savings at that point

        // 3. Subtract the target from the savings and return

        return 0.0
    }

    /**
     * Get the income coming by scheduled transactions between two timestamps
     */
    private fun scheduledIncomeBetween(
        startTimestamp: Long,
        endTimestamp: Timestamp,
        includePast: Boolean = false
    ): Double {

        // 1. Get all the scheduled income transactions

        // 2. Add all the transactions until the end timestamp and return

        return 0.0
    }

    /**
     * Get the outcome coming by scheduled transactions between two timestamps
     */
    private fun scheduledOutcomeBetween(
        startTimestamp: Long,
        endTimestamp: Timestamp,
        includePast: Boolean = false
    ): Double {

        // 1. Get all the scheduled outcome transactions

        // 2. Add all the transactions until the end timestamp and return

        return 0.0
    }

    /**
     * Get income between two timestamps
     */
    private fun incomeBetween(startTimestamp: Long, endTimestamp: Timestamp): Double {

        // 1. Get all the past income transactions since startTimestamp
        val income = 0.0

        // 2. Get the pending scheduled transactions until end timestamp
        val pendingIncome = scheduledIncomeBetween(startTimestamp, endTimestamp)

        // 3. Add all the transactions and return
        return income + pendingIncome
    }

    /**
     * Get the outcome between two timestamps
     */
    private fun outcomeBetween(startTimestamp: Long, endTimestamp: Timestamp): Double {

        // 1. Get all the past outcome transactions since startTimestamp
        val outcome = 0.0

        // 2. Get the pending scheduled transactions until end timestamp
        val pendingOutcome = scheduledOutcomeBetween(startTimestamp, endTimestamp)

        // 3. Add all the transactions and return (as positive number)
        return outcome + pendingOutcome
    }

    /**
     * Calculate and process the estimated time to reach
     * the current financial goal
     */
    private fun estimatedTimeForGoal(financialGoal: FinancialGoal): Long {

        // 1. get historical savings since goal start

        // 2. make an average

        // 3. calculate savings per day

        // 4. calculate how many time left

        return 0L
    }
}