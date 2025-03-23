@file:Suppress("unused")

package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.model.data.FinancialGoal

@Entity(
    tableName = "financial_goal",
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("account_id"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class FinancialGoalEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "account_id") var accountId: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "target_value") var targetValue: Double = 0.0,
    @ColumnInfo(name = "current_value") var currentValue: Double = 0.0,
    @ColumnInfo(name = "month_spend_limit") var monthSpendLimit: Double = 0.0,
    @ColumnInfo(name = "month_savings") var monthSavings: Double = 0.0,
    @ColumnInfo(name = "target_timestamp") var targetTimestamp: Long? = null,
    @ColumnInfo(name = "estimated_timestamp") var estimatedTimestamp: Long? = null
)

fun FinancialGoalEntity.toModel() = FinancialGoal(
    id = id,
    accountId = accountId,
    name = name,
    targetValue = targetValue,
    currentValue = currentValue,
    monthSpendLimit = monthSpendLimit,
    monthSavings = monthSavings,
    targetTimestamp = targetTimestamp,
    estimatedTimestamp = estimatedTimestamp
)

fun FinancialGoal.toEntity() = FinancialGoalEntity(
    id = id,
    accountId = accountId,
    name = name,
    targetValue = targetValue,
    currentValue = currentValue,
    monthSpendLimit = monthSpendLimit,
    monthSavings = monthSavings,
    targetTimestamp = targetTimestamp,
    estimatedTimestamp = estimatedTimestamp
)