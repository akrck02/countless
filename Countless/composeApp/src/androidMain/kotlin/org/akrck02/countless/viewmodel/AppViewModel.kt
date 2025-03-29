package org.akrck02.countless.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.akrck02.countless.data.FinancialProcessor
import org.akrck02.countless.data.model.data.Account
import org.akrck02.countless.data.repository.AccountRepository

private const val DEFAULT_ACCOUNT_ID = 1

class AppViewModel(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    var financialProcessor: FinancialProcessor = FinancialProcessor()
    var currentAccount: Account? = null
    var loaded: Boolean by mutableStateOf(false)

    init {
        start()
    }

    fun isFirstTime(): Boolean = currentAccount == null

    private suspend fun loadAccountDataIfPresent() {

        // The account already loaded
        if (null != currentAccount) return

        // Find if the account exists, create otherwise.
        currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID)

    }

    fun start() {
        viewModelScope.launch {
            loadAccountDataIfPresent()
            financialProcessor.sync()
            loaded = true
        }
    }
}