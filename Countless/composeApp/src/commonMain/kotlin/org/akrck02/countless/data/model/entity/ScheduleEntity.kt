@file:Suppress("unused")

package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.model.data.Schedule
import org.akrck02.countless.data.model.option.ScheduleType

@Entity(
    tableName = "schedule",
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("account_id"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "account_id") var accountId: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "type") var type: ScheduleType = ScheduleType.UNKNOWN,
    @ColumnInfo(name = "start_timestamp") var startTimestamp: Long? = null,
    @ColumnInfo(name = "end_timestamp") var endTimestamp: Long? = null
)

fun ScheduleEntity.toModel() = Schedule(
    id = id,
    accountId = accountId,
    name = name,
    type = type,
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp
)

fun Schedule.toEntity() = ScheduleEntity(
    id = id,
    accountId = accountId,
    name = name,
    type = type,
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp
)