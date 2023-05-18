package com.dojo.globant.reminders.feature.reminder.add.ui

import com.dojo.globant.reminders.common.util.UiText
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

data class AddReminderState(
    val title: String = "",
    val titleError: UiText? = null,
    val description: String = "",
    val descriptionError: UiText? = null,
    val type: Reminder.TypeReminder? = null,
    val typeError: UiText? = null,
    val date: Long = 0L,
    val dateError: UiText? = null,
    val time: Long = 0L,
    val timeError: UiText? = null,
)
