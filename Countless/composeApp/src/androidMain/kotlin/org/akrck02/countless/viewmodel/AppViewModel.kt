package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.akrck02.countless.data.model.data.Account
import org.akrck02.countless.data.repository.AccountRepository

private const val DEFAULT_ACCOUNT_ID = 1
private const val DEFAULT_ACCOUNT_NAME = "your account"

class AppViewModel(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    var currentAccount: Account? = null

    init {
        viewModelScope.launch {
            loadBasicAccountData()
        }
    }

    suspend fun loadBasicAccountData() {

        // The account already loaded
        if (null != currentAccount) return

        // Find if the account exists, create otherwise.
        currentAccount = accountRepository.find(DEFAULT_ACCOUNT_ID)

        // The account already loaded
        if (null != currentAccount) return

        // Create new account
        accountRepository.create(Account(name = DEFAULT_ACCOUNT_NAME))
    }
}