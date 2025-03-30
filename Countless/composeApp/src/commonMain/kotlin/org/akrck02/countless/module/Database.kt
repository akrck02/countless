package org.akrck02.countless.module

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.akrck02.countless.data.dao.AccountDao
import org.akrck02.countless.data.dao.FinancialGoalDao
import org.akrck02.countless.data.dao.FinancialGoalTrackRecordDao
import org.akrck02.countless.data.dao.FinancialTransactionDao
import org.akrck02.countless.data.dao.ScheduleDao
import org.akrck02.countless.data.entity.AccountEntity
import org.akrck02.countless.data.entity.FinancialGoalEntity
import org.akrck02.countless.data.entity.FinancialGoalTrackRecordEntity
import org.akrck02.countless.data.entity.FinancialTransactionEntity
import org.akrck02.countless.data.entity.ScheduleEntity

@Database(
    exportSchema = true,
    entities = [
        AccountEntity::class,
        FinancialGoalEntity::class,
        FinancialGoalTrackRecordEntity::class,
        ScheduleEntity::class,
        FinancialTransactionEntity::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAccountDataAccess(): AccountDao
    abstract fun getScheduleDataAccess(): ScheduleDao
    abstract fun getTransactionDataAccess(): FinancialTransactionDao
    abstract fun getFinancialGoalDataAccess(): FinancialGoalDao
    abstract fun getFinancialGoalTrackRecordDataAccess(): FinancialGoalTrackRecordDao
}


// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

fun getAccountDataAccess(appDatabase: AppDatabase) = appDatabase.getAccountDataAccess()
fun getScheduleDataAccess(appDatabase: AppDatabase) = appDatabase.getScheduleDataAccess()
fun getTransactionDataAccess(appDatabase: AppDatabase) = appDatabase.getTransactionDataAccess()
fun getFinancialGoalDataAccess(appDatabase: AppDatabase) = appDatabase.getFinancialGoalDataAccess()
fun getFinancialGoalTrackRecordDataAccess(appDatabase: AppDatabase) =
    appDatabase.getFinancialGoalTrackRecordDataAccess()
