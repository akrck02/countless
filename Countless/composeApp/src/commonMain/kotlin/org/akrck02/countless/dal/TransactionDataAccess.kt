package org.akrck02.countless.dal

import org.akrck02.countless.Countless
import org.akrck02.countless.extension.assertPositive
import org.akrck02.countless.model.Transaction

private const val ACCOUNT_ID = "account id"
private const val TRANSACTION_ID = "transaction id"

@Suppress("unused")
class TransactionDataAccess(val database: Countless) {

    fun findTransaction(transactionId: Int): Transaction {
        transactionId.assertPositive(TRANSACTION_ID)
        return Transaction()
    }

    fun findAccountTransactions(accountId: Int): MutableList<Transaction> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }

    fun findAccountSavings(accountId: Int): MutableList<Transaction> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }

    fun findAccountExpenses(accountId: Int): MutableList<Transaction> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }


    fun updateTransaction(transactionId: Int, transaction: Transaction) {
        transactionId.assertPositive(TRANSACTION_ID)
    }

    fun createTransaction(transaction: Transaction): Int {
        transaction.accountId.assertPositive(ACCOUNT_ID)
        return 1
    }


    fun killTransaction(transactionId: Int) {
        transactionId.assertPositive(TRANSACTION_ID)
    }

}