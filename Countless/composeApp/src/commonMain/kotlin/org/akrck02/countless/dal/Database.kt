@file:Suppress("EXPECT_AND_ACTUAL_IN_THE_SAME_MODULE", "unused")

package org.akrck02.countless.dal

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

interface DataAccessLayer {
    fun getAccountDataAccess(): AccountDataAccess
    fun getScheduleDataAccess(): ScheduleDataAccess
    fun getTransactionDataAccess(): TransactionDataAccess
    fun getFinancialGoalDataAccess(): FinancialGoalDataAccess
}