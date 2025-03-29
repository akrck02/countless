package org.akrck02.countless.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.akrck02.countless.data.extension.assertNullOrPositive
import org.akrck02.countless.data.extension.assertPositive
import org.akrck02.countless.data.model.data.FinancialGoalTrackRecord

@Entity(
    tableName = "financial_goal_track_record",
    foreignKeys = [ForeignKey(
        entity = FinancialGoalEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("financial_goal_id"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
class FinancialGoalTrackRecordEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "financial_goal_id", index = true) var financialGoalId: Int,
    @ColumnInfo(name = "target_value", defaultValue = "0") var targetValue: Double = 0.0,
    @ColumnInfo(name = "current_value", defaultValue = "0") var currentValue: Double = 0.0,
    @ColumnInfo(name = "insert_timestamp") var insertTimestamp: Long,
    @ColumnInfo(name = "target_timestamp") var targetTimestamp: Long,
    @ColumnInfo(name = "estimated_timestamp") var estimatedTimestamp: Long? = null
)

fun FinancialGoalTrackRecordEntity.toModel() = FinancialGoalTrackRecord(
    id = id,
    financialGoalId = financialGoalId,
    targetValue = targetValue,
    currentValue = currentValue,
    targetTimestamp = targetTimestamp,
    estimatedTimestamp = estimatedTimestamp,
    insertTimestamp = insertTimestamp
)

fun FinancialGoalTrackRecord.toEntity(): FinancialGoalTrackRecordEntity {

    id.assertNullOrPositive("FinancialGoalTrackRecord.id")
    financialGoalId.assertPositive("FinancialGoalTrackRecord.financialGoalId")
    currentValue.assertPositive("FinancialGoalTrackRecord.currentValue")
    targetValue.assertPositive("FinancialGoalTrackRecord.targetValue")
    targetTimestamp.assertPositive("FinancialGoalTrackRecord.targetTimestamp")
    insertTimestamp.assertPositive("FinancialGoalTrackRecord.insertTimestap")

    return FinancialGoalTrackRecordEntity(
        id = id ?: 0,
        financialGoalId = financialGoalId!!,
        targetValue = targetValue,
        currentValue = currentValue,
        targetTimestamp = targetTimestamp!!,
        estimatedTimestamp = estimatedTimestamp,
        insertTimestamp = insertTimestamp!!
    )
}