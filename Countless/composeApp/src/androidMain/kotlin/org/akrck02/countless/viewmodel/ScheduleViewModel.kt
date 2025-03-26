package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.data.FinancialTransaction
import java.util.UUID
import kotlin.random.Random

class ScheduleViewModel : ViewModel() {
    fun getScheduledTransactions(period: org.akrck02.countless.ui.view.Period): List<FinancialTransaction> {

        val items: MutableList<FinancialTransaction> = mutableListOf()

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