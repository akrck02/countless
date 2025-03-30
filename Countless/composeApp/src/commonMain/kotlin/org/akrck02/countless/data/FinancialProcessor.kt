package org.akrck02.countless.data

import org.akrck02.countless.data.model.FinancialGoal
import java.sql.Timestamp

class FinancialProcessor {

    var yearIncome: Double = 0.0
    var monthIncome: Double = 0.0

    var yearOutcome: Double = 0.0
    var monthOutcome: Double = 0.0

    var estimatedTimestamp: Long = 0L
    var estimatedBudgetForToday: Double = 0.0
    var estimatedBudgetForMonth: Double = 0.0

    fun sync() {
        closeScheduledTransactions()
        updateIncomes()
        updateOutComes()
        updateEstimatedTimes()
        updateEstimatedBudget()
    }

    fun closeScheduledTransactions() {

    }

    fun updateIncomes() {

    }

    fun updateOutComes() {

    }

    fun updateEstimatedTimes() {

    }

    fun updateEstimatedBudget() {

    }


    fun targetForDay(financialGoal: FinancialGoal, timestamp: Long): Double {

        // 1. Get all the days between goal start and goal end

        // 2. Calculate the savings per day to be made

        // 3. Add until current day

        return 0.0
    }

    fun getBudgetDifferenceForTime(financialGoal: FinancialGoal, timestamp: Long): Double {

        // 1. Get the target for the given day

        // 2. Get the savings at that point

        // 3. Subtract the target from the savings and return

        return 0.0
    }

    fun scheduledIncomeBetween(
        startTimestamp: Long,
        endTimestamp: Timestamp,
        includePast: Boolean = false
    ): Double {

        // 1. Get all the scheduled income transactions

        // 2. Add all the transactions until the end timestamp and return

        return 0.0
    }

    fun scheduledOutcomeBetween(
        startTimestamp: Long,
        endTimestamp: Timestamp,
        includePast: Boolean = false
    ): Double {

        // 1. Get all the scheduled outcome transactions

        // 2. Add all the transactions until the end timestamp and return

        return 0.0
    }

    fun incomeBetween(startTimestamp: Long, endTimestamp: Timestamp): Double {

        // 1. Get all the past income transactions since startTimestamp
        val income = 0.0

        // 2. Get the pending scheduled transactions until end timestamp
        val pendingIncome = scheduledIncomeBetween(startTimestamp, endTimestamp)

        // 3. Add all the transactions and return
        return income + pendingIncome
    }

    fun outcomeBetween(startTimestamp: Long, endTimestamp: Timestamp): Double {

        // 1. Get all the past outcome transactions since startTimestamp
        val outcome = 0.0

        // 2. Get the pending scheduled transactions until end timestamp
        val pendingOutcome = scheduledOutcomeBetween(startTimestamp, endTimestamp)

        // 3. Add all the transactions and return (as positive number)
        return outcome + pendingOutcome
    }

    fun estimatedTimeForGoal(financialGoal: FinancialGoal): Long {

        // 1. get historical savings since goal start

        // 2. make an average

        // 3. calculate savings per day

        // 4. calculate how many time left

        return 0L
    }

}