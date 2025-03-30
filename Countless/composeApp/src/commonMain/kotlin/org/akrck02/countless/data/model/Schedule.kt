package org.akrck02.countless.data.model

import kotlinx.serialization.Serializable
import org.akrck02.countless.data.model.option.ScheduleType

@Serializable
data class Schedule(
    var id: Int? = null,
    var accountId: Int? = null,
    var name: String? = null,
    var type: ScheduleType = ScheduleType.UNKNOWN,
    var startTimestamp: Long? = null,
    var endTimestamp: Long? = null
)
