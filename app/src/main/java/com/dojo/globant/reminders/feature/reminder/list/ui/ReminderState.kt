package com.dojo.globant.reminders.feature.reminder.list.ui

import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.util.toDateInString
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

data class ReminderState(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val image: Int = R.drawable.round_access_time,
    val type: Reminder.TypeReminder? = null,
    val date: String = ""
)

fun Reminder.toModel() = ReminderState(
    id = id,
    title = title,
    description = description,
    type = type,
    date = (date + time).toDateInString()
)
