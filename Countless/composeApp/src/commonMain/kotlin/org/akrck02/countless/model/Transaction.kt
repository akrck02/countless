package org.akrck02.countless.model

data class Transaction(
    var id: Int? = null,
    var accountId: Int? = null,
    var name: String? = null,
    var value: Double = 0.0,
    var timestamp: Long? = null,
    var scheduleId: Int? = null,
)