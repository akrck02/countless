@file:Suppress("unused")

package org.akrck02.countless.data.repository

import org.akrck02.countless.data.dao.ScheduleDao
import org.akrck02.countless.data.entity.toEntity
import org.akrck02.countless.data.entity.toModel
import org.akrck02.countless.data.model.Schedule
import org.akrck02.countless.data.model.option.ScheduleType

class ScheduleRepository(private val scheduleDao: ScheduleDao) {

    suspend fun find(scheduleId: Int): Schedule? {
        return scheduleDao.find(scheduleId)?.toModel()
    }

    suspend fun findByAccount(accountId: Int): MutableList<Schedule> {
        return scheduleDao.findByAccount(accountId).map { it.toModel() }.toMutableList()
    }
    
    suspend fun findByAccountAndType(
        accountId: Int,
        type: ScheduleType
    ): MutableList<Schedule> {
        return scheduleDao.findByAccountAndType(accountId, type).map { it.toModel() }.toMutableList()
    }

    suspend fun update(schedule: Schedule) {
        return scheduleDao.update(schedule.toEntity())
    }

    suspend fun create(schedule: Schedule) {
        return scheduleDao.create(schedule.toEntity())
    }

    suspend fun kill(schedule: Schedule) {
        return scheduleDao.kill(schedule.toEntity())
    }


}