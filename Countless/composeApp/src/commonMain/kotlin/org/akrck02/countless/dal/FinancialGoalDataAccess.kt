package org.akrck02.countless.dal

import org.akrck02.countless.Countless
import org.akrck02.countless.extension.assertPositive
import org.akrck02.countless.model.Account
import org.akrck02.countless.model.FinancialGoal

private const val FINANCIAL_GOAL_ID = "financial goal id"
private const val ACCOUNT_ID = "account id"

@Suppress("unused")
class FinancialGoalDataAccess(val database: Countless) {

    fun findFinancialGoal(financialGoalId: Int): FinancialGoal {
        financialGoalId.assertPositive(FINANCIAL_GOAL_ID)
        return FinancialGoal()
    }

    fun findAccountFinancialGoals(accountId: Int): MutableList<FinancialGoal> {
        accountId.assertPositive(ACCOUNT_ID)
        return mutableListOf()
    }

    fun createAccount(account: Account): Int {
        return 1
    }

}