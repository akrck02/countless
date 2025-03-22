package org.akrck02.countless.dal

import org.akrck02.countless.Countless
import org.akrck02.countless.extension.assertPositive
import org.akrck02.countless.model.Schedule
import org.akrck02.countless.model.ScheduleType

private const val SCHEDULE_ID = "schedule id"
private const val ACCOUNT_ID = "account id"

@Suppress("unused")
class ScheduleDataAccess(val database: Countless) {

    fun findSchedule(scheduleId: Int): Schedule {
        scheduleId.assertPositive(SCHEDULE_ID)
        return Schedule()
    }

    fun findSchedules(accountId: Int): MutableList<Schedule> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }

    fun findSchedules(accountId: Int, type: ScheduleType): MutableList<Schedule> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }

    fun updateSchedule(scheduleId: Int, schedule: Schedule) {
        scheduleId.assertPositive(SCHEDULE_ID)
    }

    fun createSchedule(schedule: Schedule): Int {
        schedule.accountId.assertPositive(ACCOUNT_ID)
        return 1
    }
}