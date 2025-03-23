package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import org.akrck02.countless.data.repository.AccountRepository
import org.akrck02.countless.data.repository.FinancialGoalRepository

class StatsViewModel(
    private val accountRepository: AccountRepository,
    private val goalRepository: FinancialGoalRepository
) : ViewModel()