@file:Suppress("unused")

package org.akrck02.countless.data.repository

import org.akrck02.countless.data.dao.FinancialGoalDao
import org.akrck02.countless.data.entity.toEntity
import org.akrck02.countless.data.entity.toModel
import org.akrck02.countless.data.extension.assertPositive
import org.akrck02.countless.data.model.FinancialGoal

private const val ACCOUNT_ID = "account id"
private const val FINANCIAL_GOAL_ID = "account id"

class FinancialGoalRepository(
    private val financialGoalDao: FinancialGoalDao
) {
    suspend fun find(financialGoalId: Int): FinancialGoal? {
        financialGoalId.assertPositive(FINANCIAL_GOAL_ID)
        return financialGoalDao.find(financialGoalId)?.toModel()
    }

    suspend fun findByAccount(accountId: Int): MutableList<FinancialGoal> {
        accountId.assertPositive(ACCOUNT_ID)
        return financialGoalDao.findByAccount(accountId).map { it.toModel() }.toMutableList()
    }

    suspend fun update(financialGoal: FinancialGoal) {
        financialGoal.id.assertPositive(FINANCIAL_GOAL_ID)
        financialGoal.accountId.assertPositive(ACCOUNT_ID)
        financialGoalDao.update(financialGoal.toEntity())
    }

    suspend fun create(financialGoal: FinancialGoal) {
        financialGoalDao.create(financialGoal.toEntity())
    }

    suspend fun kill(financialGoal: FinancialGoal) {
        financialGoalDao.kill(financialGoal.toEntity())
    }
}