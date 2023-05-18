package com.dojo.globant.reminders.feature.reminder.list.domain.model

import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderState

data class Reminder(
    val id: Int,
    val title: String,
    val description: String,
    val type: TypeReminder,
    val image: Int,
    val date: Long,
    val time: Long
) {
    enum class TypeReminder {
        WORKING, PERSONAL
    }
}

fun AddReminderState.toDomain() = Reminder(
    id = 0,
    title = title,
    description = description,
    type = type ?: Reminder.TypeReminder.PERSONAL,
    image = R.drawable.round_access_time,
    date = date,
    time = time
)