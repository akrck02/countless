package org.akrck02.countless.data.model.data

class FinancialGoalTrackRecord(
    var id: Int? = null,
    var financialGoalId: Int? = null,
    var targetValue: Double = 0.0,
    var currentValue: Double = 0.0,
    var targetTimestamp: Long? = null,
    var estimatedTimestamp: Long? = null,
    var insertTimestamp: Long? = null
)
