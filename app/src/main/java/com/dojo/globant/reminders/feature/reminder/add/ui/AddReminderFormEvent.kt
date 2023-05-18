package com.dojo.globant.reminders.feature.reminder.add.ui

import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

sealed class AddReminderFormEvent {
    data class TitleChanged(val title: String) : AddReminderFormEvent()
    data class DescriptionChanged(val description: String) : AddReminderFormEvent()
    data class TypeChanged(val type: Reminder.TypeReminder) : AddReminderFormEvent()
    data class DateChanged(val date: Long) : AddReminderFormEvent()
    data class TimeChanged(val time: Long) : AddReminderFormEvent()
    object OnCreateReminder : AddReminderFormEvent()
}
