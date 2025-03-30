package org.akrck02.countless.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FinancialTransaction(
    var id: Int? = null,
    var accountId: Int? = null,
    var name: String? = null,
    var value: Double = 0.0,
    var timestamp: Long? = null,
    var scheduleId: Int? = null,
)