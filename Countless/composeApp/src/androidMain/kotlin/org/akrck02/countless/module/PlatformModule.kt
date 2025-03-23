@file:Suppress("unused")

package org.akrck02.countless.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.akrck02.countless.data.repository.AccountRepository
import org.akrck02.countless.data.repository.FinancialGoalRepository
import org.akrck02.countless.data.repository.ScheduleRepository
import org.akrck02.countless.data.repository.TransactionRepository
import org.akrck02.countless.viewmodel.GoalsViewModel
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.akrck02.countless.viewmodel.StatsViewModel
import org.akrck02.countless.viewmodel.WalletViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder(get())
    }
}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("countless.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        platformModule(),
        provideRepositoryModule,
        provideDatabaseModule
    )
}


// Add data repositories here to be loaded
val provideRepositoryModule = module {
    singleOf(::AccountRepository)
    singleOf(::TransactionRepository)
    singleOf(::ScheduleRepository)
    singleOf(::FinancialGoalRepository)
}

// Add data access classes here to be loaded inside the database module.
val provideDatabaseModule = module {
    single { getRoomDatabase(get()) }
    single { getAccountDataAccess(get()) }
    single { getScheduleDataAccess(get()) }
    single { getTransactionDataAccess(get()) }
    single { getFinancialGoalDataAccess(get()) }
}

// Add viewmodel classes here to be loaded inside the database module.
val appModule = module {
    viewModelOf(::StatsViewModel)
    viewModelOf(::WalletViewModel)
    viewModelOf(::ScheduleViewModel)
    viewModelOf(::GoalsViewModel)
}