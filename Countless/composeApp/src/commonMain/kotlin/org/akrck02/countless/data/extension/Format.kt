package org.akrck02.countless.data.extension

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.text.DecimalFormat

fun Double?.defaultDigitFormat(): String {

    if (null == this) return ""

    return DecimalFormat("#,###,###.##").format(this)
}

fun Long?.asDate(format: Int = DateFormat.SHORT): String {

    if (null == this) return ""

    return try {
        SimpleDateFormat.getDateInstance(format).format(this)
    } catch (_: Exception) {
        ""
    }

    return ""
}
