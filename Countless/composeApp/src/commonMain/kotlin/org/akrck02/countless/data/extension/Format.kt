package org.akrck02.countless.data.extension

import android.icu.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat

fun Double?.defaultDigitFormat(): String {

    if (null == this) return ""

    return DecimalFormat("#,###,###.##").format(this)
}

fun Long?.asDate(): String {

    if (null == this) return ""

    return try {
        SimpleDateFormat.getDateInstance(DateFormat.SHORT).format(this)
    } catch (_: Exception) {
        ""
    }

    return ""
}