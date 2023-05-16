package com.dojo.globant.reminders.feature.reminder.add.ui

sealed class AddReminderFormEvent {
    data class TitleChanged(val title: String) : AddReminderFormEvent()
    data class DescriptionChanged(val description: String) : AddReminderFormEvent()
    data class TypeChanged(val type: String) : AddReminderFormEvent()
    object OnCreateReminder : AddReminderFormEvent()
}
