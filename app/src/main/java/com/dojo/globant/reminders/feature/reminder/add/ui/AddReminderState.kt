package com.dojo.globant.reminders.feature.reminder.add.ui

import com.dojo.globant.reminders.common.util.UiText

data class AddReminderState(
    val title: String = "",
    val titleError: UiText? = null,
    val description: String = "",
    val descriptionError: UiText? = null,
    val type: String = "",
    val typeError: UiText? = null,
    val date: Long = 0L,
    val dateError: UiText? = null,
    val time: Long = 0L,
    val timeError: UiText? = null,
)
