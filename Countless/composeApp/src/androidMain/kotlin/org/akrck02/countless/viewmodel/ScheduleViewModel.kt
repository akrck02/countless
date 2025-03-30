package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.FinancialTransaction
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType

class ScheduleViewModel : ViewModel() {
    fun getScheduledTransactions(
        selectedTransactionType: TransactionType,
        period: Period
    ): List<FinancialTransaction> {

        val items: MutableList<FinancialTransaction> = mutableListOf()
        items.add(
            FinancialTransaction(
                id = 1,
                name = "Spotify unlimited 1990",
                timestamp = System.currentTimeMillis(),
                value = 1901.99
            )
        )

        return items.toList()
    }
}