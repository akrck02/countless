package org.akrck02.countless.data.model.data

import org.akrck02.countless.data.model.option.ScheduleType

data class Schedule(
    var id: Int? = null,
    var accountId: Int? = null,
    var name: String? = null,
    var type: ScheduleType = ScheduleType.UNKNOWN,
    var startTimestamp: Long? = null,
    var endTimestamp: Long? = null
)
