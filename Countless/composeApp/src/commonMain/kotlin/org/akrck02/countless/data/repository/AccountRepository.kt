@file:Suppress("unused")

package org.akrck02.countless.data.repository

import org.akrck02.countless.data.dao.AccountDao
import org.akrck02.countless.data.entity.toEntity
import org.akrck02.countless.data.entity.toModel
import org.akrck02.countless.data.extension.assertPositive
import org.akrck02.countless.data.model.Account


private const val ACCOUNT_ID = "account id"

class AccountRepository(
    private val accountDao: AccountDao
) {

    suspend fun find(accountId: Int): Account? {
        accountId.assertPositive(ACCOUNT_ID)
        return accountDao.find(accountId)?.toModel()
    }

    suspend fun update(account: Account) {
        account.id.assertPositive(ACCOUNT_ID)
        accountDao.update(account.toEntity())
    }

    suspend fun create(account: Account) {
        accountDao.create(account.toEntity())
    }

    suspend fun kill(account: Account) {
        accountDao.kill(account.toEntity())
    }

}
