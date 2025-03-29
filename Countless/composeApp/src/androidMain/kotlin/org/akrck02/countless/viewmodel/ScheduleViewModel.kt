package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.data.FinancialTransaction
import org.akrck02.countless.ui.options.Period
import org.akrck02.countless.ui.options.TransactionType
import java.util.UUID
import kotlin.random.Random

class ScheduleViewModel : ViewModel() {
    fun getScheduledTransactions(
        selectedTransactionType: TransactionType,
        period: Period
    ): List<FinancialTransaction> {

        val items: MutableList<FinancialTransaction> = mutableListOf()

        if (selectedTransactionType == TransactionType.All) {
            return listOf()
        }

        for (i in 1..100) {
            items.add(FinancialTransaction().apply {
                name = UUID.randomUUID().toString()
                value = Random.nextDouble(-1000.00, 1000.00)
                timestamp = System.currentTimeMillis()
            })
        }

        return items.toList()
    }
}