package org.akrck02.countless.data.model.data

import kotlin.math.min

data class FinancialGoal(
    var id: Int? = null,
    var accountId: Int? = null,
    var name: String? = null,
    var targetValue: Double = 0.0,
    var currentValue: Double = 0.0,
    var monthSpendLimit: Double = 0.0,
    var monthSavings: Double = 0.0,
    var targetTimestamp: Long? = null,
    var estimatedTimestamp: Long? = null
)


fun FinancialGoal.getCurrentProgress(): Double {
    var initial = currentValue
    var target = targetValue

    if (target == 0.0) return 0.0

    return initial / target
}

fun FinancialGoal.getCurrentProgressPercent(limit: Int = 100): Int {
    return min((getCurrentProgress() * 100).toInt(), limit)
}

fun FinancialGoal.getHumanReadableEstimatedTimeDate(): String {


    return "2 years 2 months"
}