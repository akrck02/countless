package org.akrck02.countless.dal

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.akrck02.countless.Countless

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(Countless.Schema, context, "Countless")
}

class AndroidDataAccessLayer(
    private val context: Context,
) : DataAccessLayer {
    private val database by lazy { Countless(driver = DatabaseDriverFactory(context).createDriver()) }
    override fun getAccountDataAccess() = AccountDataAccess(database)
    override fun getScheduleDataAccess() = ScheduleDataAccess(database)
    override fun getTransactionDataAccess() = TransactionDataAccess(database)
    override fun getFinancialGoalDataAccess() = FinancialGoalDataAccess(database)
}