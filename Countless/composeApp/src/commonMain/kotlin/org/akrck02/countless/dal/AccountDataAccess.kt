package org.akrck02.countless.dal

import org.akrck02.countless.Countless
import org.akrck02.countless.extension.assertPositive
import org.akrck02.countless.model.Account

private const val ACCOUNT_ID = "account id"

@Suppress("unused")
class AccountDataAccess(private val database: Countless) {

    fun findAccount(accountId: Int): Account {
        accountId.assertPositive(ACCOUNT_ID)
        database.transaction {


        }
        return Account()
    }

    fun updateAccount(accountId: Int, account: Account) {
        accountId.assertPositive(ACCOUNT_ID)
    }

    fun createAccount(account: Account): Int {
        return 1
    }

    fun killAccount(accountId: Int) {
        accountId.assertPositive(ACCOUNT_ID)
    }

}