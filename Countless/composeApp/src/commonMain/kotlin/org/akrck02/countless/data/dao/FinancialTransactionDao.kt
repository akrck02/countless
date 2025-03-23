package org.akrck02.countless.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.akrck02.countless.data.model.entity.FinancialTransactionEntity

@Dao
interface FinancialTransactionDao {

    @Query("SELECT * FROM financial_transaction WHERE id = :transactionId")
    suspend fun find(transactionId: Int): FinancialTransactionEntity

    @Query("SELECT * FROM financial_transaction WHERE account_id = :accountId")
    suspend fun findByAccount(accountId: Int): List<FinancialTransactionEntity>

    @Query("SELECT * FROM financial_transaction WHERE account_id = :accountId AND value > 0")
    suspend fun findSavingsByAccount(accountId: Int): MutableList<FinancialTransactionEntity>

    @Query("SELECT * FROM financial_transaction WHERE account_id = :accountId AND value < 0")
    suspend fun findExpensesByAccount(accountId: Int): MutableList<FinancialTransactionEntity>

    @Update
    suspend fun update(transaction: FinancialTransactionEntity)

    @Insert
    suspend fun create(transaction: FinancialTransactionEntity)

    @Delete
    suspend fun kill(transaction: FinancialTransactionEntity)

}