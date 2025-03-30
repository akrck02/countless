package org.akrck02.countless.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.akrck02.countless.data.model.Account
import org.akrck02.countless.data.model.FinancialGoal

@Serializable
class TutorialViewModel : ViewModel() {

    var account = Account()
    var financialGoal = FinancialGoal()
    
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}