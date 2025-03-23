package org.akrck02.countless.data.model.data

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
