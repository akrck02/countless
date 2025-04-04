@file:Suppress("unused")

package org.akrck02.countless.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.extension.assertNotBlank
import org.akrck02.countless.data.extension.assertNullOrPositive
import org.akrck02.countless.data.extension.assertPositive
import org.akrck02.countless.data.model.FinancialGoal

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
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "account_id", index = true) var accountId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "target_value", defaultValue = "0") var targetValue: Double = 0.0,
    @ColumnInfo(name = "current_value", defaultValue = "0") var currentValue: Double = 0.0,
    @ColumnInfo(name = "month_spend_limit", defaultValue = "0") var monthSpendLimit: Double = 0.0,
    @ColumnInfo(name = "month_savings", defaultValue = "0") var monthSavings: Double = 0.0,
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

fun FinancialGoal.toEntity(): FinancialGoalEntity {

    id.assertNullOrPositive("id")
    accountId.assertPositive("accountId")
    name.assertNotBlank("name")

    return FinancialGoalEntity(
        id = id ?: 0,
        accountId = accountId!!,
        name = name!!,
        targetValue = targetValue,
        currentValue = currentValue,
        monthSpendLimit = monthSpendLimit,
        monthSavings = monthSavings,
        targetTimestamp = targetTimestamp,
        estimatedTimestamp = estimatedTimestamp
    )
}