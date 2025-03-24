package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.model.data.FinancialGoal
import org.akrck02.countless.data.repository.FinancialGoalRepository
import java.util.UUID
import kotlin.random.Random

class GoalsViewModel(private val financialGoalRepository: FinancialGoalRepository) : ViewModel() {

    var financialGoal: FinancialGoal? = FinancialGoal().apply {
        name = "Buy a new house \uD83C\uDFE1"
        targetValue = 65000.00
        currentValue = 15324.28
        monthSavings = 900.00
        monthSpendLimit = 300.00
    }

    fun mock(): FinancialGoal {

        val target = Random.nextDouble(100.0, 65000.0)
        val current = target - Random.nextDouble(0.0, target)

        return FinancialGoal().apply {
            name = UUID.randomUUID().toString()
            targetValue = target
            currentValue = current
        }

    }

}