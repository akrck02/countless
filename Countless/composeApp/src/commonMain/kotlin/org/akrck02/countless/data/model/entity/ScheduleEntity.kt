@file:Suppress("unused")

package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.extension.assertNotBlank
import org.akrck02.countless.data.extension.assertNullOrPositive
import org.akrck02.countless.data.extension.assertPositive
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
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "account_id", index = true) var accountId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: ScheduleType = ScheduleType.UNKNOWN,
    @ColumnInfo(name = "start_timestamp") var startTimestamp: Long,
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

fun Schedule.toEntity(): ScheduleEntity {

    id.assertNullOrPositive("schedule.id")
    accountId.assertPositive("schedule.accountId")
    name.assertNotBlank("schedule.name")
    startTimestamp.assertPositive("schedule.startTimestamp")

    return ScheduleEntity(
        id = id ?: 0,
        accountId = accountId!!,
        name = name!!,
        type = type,
        startTimestamp = startTimestamp!!,
        endTimestamp = endTimestamp
    )
}