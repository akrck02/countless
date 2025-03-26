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
import org.akrck02.countless.data.model.entity.AccountEntity
import org.akrck02.countless.data.model.entity.FinancialGoalEntity
import org.akrck02.countless.data.model.entity.FinancialGoalTrackRecordEntity
import org.akrck02.countless.data.model.entity.FinancialTransactionEntity
import org.akrck02.countless.data.model.entity.ScheduleEntity
import org.akrck02.countless.module.migrations.DB_1_TO_2
import org.akrck02.countless.module.migrations.DB_2_to_3
import org.akrck02.countless.module.migrations.dbMigration

@Database(
    entities = [
        AccountEntity::class,
        FinancialGoalEntity::class,
        FinancialGoalTrackRecordEntity::class,
        ScheduleEntity::class,
        FinancialTransactionEntity::class
    ],
    version = 3
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
        .addMigrations(
            dbMigration(1, 2, DB_1_TO_2),
            dbMigration(2, 3, DB_2_to_3)
        )
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
