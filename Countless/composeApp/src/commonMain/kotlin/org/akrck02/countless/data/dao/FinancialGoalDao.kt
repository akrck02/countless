@file:Suppress("unused")

package org.akrck02.countless.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.akrck02.countless.data.model.entity.FinancialGoalEntity

@Dao
interface FinancialGoalDao {

    @Query("SELECT * FROM FINANCIAL_GOAL WHERE id = :financialGoalId")
    suspend fun find(financialGoalId: Int): FinancialGoalEntity

    @Query("SELECT * FROM FINANCIAL_GOAL WHERE account_id = :accountId")
    suspend fun findByAccount(accountId: Int): MutableList<FinancialGoalEntity>

    @Update
    suspend fun update(financialGoalEntity: FinancialGoalEntity)

    @Insert
    suspend fun create(financialGoal: FinancialGoalEntity)

    @Delete
    suspend fun kill(financialGoal: FinancialGoalEntity)

}