package org.akrck02.countless.module

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import kotlinx.coroutines.Dispatchers
import org.akrck02.countless.data.dao.AccountDao
import org.akrck02.countless.data.dao.FinancialGoalDao
import org.akrck02.countless.data.dao.FinancialTransactionDao
import org.akrck02.countless.data.dao.ScheduleDao
import org.akrck02.countless.data.model.entity.AccountEntity
import org.akrck02.countless.data.model.entity.FinancialGoalEntity
import org.akrck02.countless.data.model.entity.FinancialTransactionEntity
import org.akrck02.countless.data.model.entity.ScheduleEntity

@Database(
    entities = [
        AccountEntity::class,
        FinancialGoalEntity::class,
        ScheduleEntity::class,
        FinancialTransactionEntity::class
    ],
    version = 2
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAccountDataAccess(): AccountDao
    abstract fun getScheduleDataAccess(): ScheduleDao
    abstract fun getTransactionDataAccess(): FinancialTransactionDao
    abstract fun getFinancialGoalDataAccess(): FinancialGoalDao
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
            MIGRATION_1_2
        )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL(
            "ALTER TABLE account ADD COLUMN name TEXT"
        )
    }
}


fun getAccountDataAccess(appDatabase: AppDatabase) = appDatabase.getAccountDataAccess()
fun getScheduleDataAccess(appDatabase: AppDatabase) = appDatabase.getScheduleDataAccess()
fun getTransactionDataAccess(appDatabase: AppDatabase) = appDatabase.getTransactionDataAccess()
fun getFinancialGoalDataAccess(appDatabase: AppDatabase) = appDatabase.getFinancialGoalDataAccess()