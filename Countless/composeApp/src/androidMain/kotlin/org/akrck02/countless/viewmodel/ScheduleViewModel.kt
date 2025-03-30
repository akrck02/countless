package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.dao.ScheduleDao
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType

class ScheduleViewModel(
    private val scheduleDao: ScheduleDao
) : ViewModel() {
    fun getScheduledTransactions(
        accountId: Int,
        selectedTransactionType: TransactionType,
        period: Period
    ): List<FinancialTransaction> {

        val items: MutableList<FinancialTransaction> = mutableListOf()// scheduleDao.findByAccountAndType(accountId,selectedTransactionType)


        return items.toList()
    }
}