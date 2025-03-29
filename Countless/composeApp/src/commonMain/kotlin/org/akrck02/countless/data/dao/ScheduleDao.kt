@file:Suppress("unused")

package org.akrck02.countless.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.akrck02.countless.data.model.entity.ScheduleEntity
import org.akrck02.countless.data.model.option.ScheduleType

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule WHERE id = :scheduleId")
    suspend fun find(scheduleId: Int): ScheduleEntity

    @Query("SELECT * FROM schedule WHERE account_id = :accountId")
    suspend fun findByAccountAndType(accountId: Int): MutableList<ScheduleEntity>

    @Query("SELECT * FROM schedule WHERE account_id = :accountId AND type = :type")
    suspend fun findByAccountAndType(
        accountId: Int,
        type: ScheduleType
    ): MutableList<ScheduleEntity>

    @Update
    suspend fun update(scheduleEntity: ScheduleEntity)

    @Insert
    suspend fun create(scheduleEntity: ScheduleEntity)

    @Delete
    suspend fun kill(scheduleEntity: ScheduleEntity)
}