package org.akrck02.countless.data.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toEpochMilli() = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toLocalDateTime(): LocalDateTime = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.daysBetween(other: LocalDateTime) = ChronoUnit.DAYS.between(this, other)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.endOfMonth(): LocalDateTime = withMonth(month.plus(1).value).withDayOfMonth(1).minusDays(1)