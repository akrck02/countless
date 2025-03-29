package org.akrck02.countless.ui.options

import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.month_selector_option
import countless.composeapp.generated.resources.year_selector_option
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

enum class Period(val resource: StringResource) {
    Month(Res.string.month_selector_option),
    Year(Res.string.year_selector_option);

    suspend fun getName(): String {
        return getString(resource)
    }
}