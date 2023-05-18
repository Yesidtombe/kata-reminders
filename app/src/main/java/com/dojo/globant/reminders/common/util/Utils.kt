package com.dojo.globant.reminders.common.util

import com.dojo.globant.reminders.common.Constants
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun LocalDate.toTime() : Long {
    return SimpleDateFormat(Constants.PATTERN_DATE, Locale.getDefault())
        .parse(this.toString())?.time ?: 0L
}

fun Int.toTimeInMin() : Long {
    return (this * 60) * 1000L
}

fun Int.toTimeInHours() : Long {
    return ((this * 60) * 60) * 1000L
}