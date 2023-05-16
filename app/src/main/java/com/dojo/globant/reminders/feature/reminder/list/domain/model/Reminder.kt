package com.dojo.globant.reminders.feature.reminder.list.domain.model

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
