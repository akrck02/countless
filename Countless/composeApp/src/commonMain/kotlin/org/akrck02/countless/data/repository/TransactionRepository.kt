@file:Suppress("unused")

package org.akrck02.countless.data.repository

import org.akrck02.countless.data.dao.FinancialTransactionDao
import org.akrck02.countless.data.entity.toEntity
import org.akrck02.countless.data.entity.toModel
import org.akrck02.countless.data.model.FinancialTransaction


class TransactionRepository(
    private val transactionDao: FinancialTransactionDao
) {

    suspend fun findAllByAccountId(accountId: Int): List<FinancialTransaction> {
        return transactionDao.findByAccount(accountId).map { it.toModel() }
    }

    suspend fun findSavingsByAccountId(accountId: Int): List<FinancialTransaction> {
        return transactionDao.findSavingsByAccount(accountId).map { it.toModel() }
    }

    suspend fun findExpensesByAccountId(accountId: Int): List<FinancialTransaction> {
        return transactionDao.findExpensesByAccount(accountId).map { it.toModel() }
    }

    suspend fun create(financialTransaction: FinancialTransaction) {
        transactionDao.create(financialTransaction.toEntity())
    }
}
