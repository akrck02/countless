package org.akrck02.countless.ui.options

import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.wallet_transaction_type_all
import countless.composeapp.generated.resources.wallet_transaction_type_expenses
import countless.composeapp.generated.resources.wallet_transaction_type_savings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString


enum class TransactionType(val resource: StringResource) {
    All(Res.string.wallet_transaction_type_all),
    Savings(Res.string.wallet_transaction_type_savings),
    Expenses(Res.string.wallet_transaction_type_expenses);

    suspend fun getName(): String {
        return getString(resource)
    }
}
