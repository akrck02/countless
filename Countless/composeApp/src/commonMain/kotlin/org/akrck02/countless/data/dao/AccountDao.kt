@file:Suppress("unused")

package org.akrck02.countless.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.akrck02.countless.data.entity.AccountEntity

@Dao
interface AccountDao {

    @Query("SELECT * FROM account WHERE id = :accountId")
    suspend fun find(accountId: Int): AccountEntity?

    @Update
    suspend fun update(accountEntity: AccountEntity)

    @Insert
    suspend fun create(accountEntity: AccountEntity)

    @Delete
    suspend fun kill(accountEntity: AccountEntity)

}
