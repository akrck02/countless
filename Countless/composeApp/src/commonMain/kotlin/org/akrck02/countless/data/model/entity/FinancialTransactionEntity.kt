@file:Suppress("unused")

package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.model.data.FinancialTransaction

@Entity(
    tableName = "financial_transaction",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account_id"),
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = ScheduleEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schedule_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class FinancialTransactionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "account_id", index = true) var accountId: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "value") var value: Double = 0.0,
    @ColumnInfo(name = "timestamp") var timestamp: Long? = null,
    @ColumnInfo(name = "schedule_id", index = true) var scheduleId: Int? = null,
)


fun FinancialTransactionEntity.toModel() = FinancialTransaction(
    id = id,
    accountId = accountId,
    name = name,
    value = value,
    timestamp = timestamp,
    scheduleId = scheduleId
)

fun FinancialTransaction.toEntity() = FinancialTransactionEntity(
    id = id,
    accountId = accountId,
    name = name,
    value = value,
    timestamp = timestamp,
    scheduleId = scheduleId
)