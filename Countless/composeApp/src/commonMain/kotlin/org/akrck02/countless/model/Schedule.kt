package org.akrck02.countless.model

enum class ScheduleType {
    UNKNOWN,
    MONTHLY,
    YEARLY
}

data class Schedule(
    var accountId: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var type: ScheduleType = ScheduleType.UNKNOWN,
    var startTimestamp: Long? = null,
    var endTimestamp: Long? = null
)
