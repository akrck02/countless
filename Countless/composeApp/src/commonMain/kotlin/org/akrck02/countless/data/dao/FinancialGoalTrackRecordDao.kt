@file:Suppress("unused")

package org.akrck02.countless.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.akrck02.countless.data.entity.FinancialGoalTrackRecordEntity

@Dao
interface FinancialGoalTrackRecordDao {

    @Query("SELECT * FROM FINANCIAL_GOAL_TRACK_RECORD WHERE financial_goal_id = :financialGoalId")
    suspend fun findByGoal(financialGoalId: Int): MutableList<FinancialGoalTrackRecordEntity>

    @Query("SELECT * FROM FINANCIAL_GOAL_TRACK_RECORD WHERE financial_goal_id = :financialGoalId AND insert_timestamp BETWEEN :startTimestamp AND :endTimestamp")
    suspend fun findByGoalBetweenDates(
        financialGoalId: Int,
        startTimestamp: Long,
        endTimestamp: Long
    ): MutableList<FinancialGoalTrackRecordEntity>

    @Insert
    suspend fun create(financialGoalTrackRecordEntity: FinancialGoalTrackRecordEntity)

    @Delete
    suspend fun kill(financialGoalTrackRecordEntity: FinancialGoalTrackRecordEntity)

}