package com.dojo.globant.reminders.feature.reminder.add.data.db.converters

import androidx.room.TypeConverter
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

class TypeReminderConverter {
    @TypeConverter
    fun convertTypeReminderToString(value: Reminder.TypeReminder): String {
        return value.name
    }

    @TypeConverter
    fun convertStringToTypeReminder(value: String): Reminder.TypeReminder {
        return try {
            Reminder.TypeReminder.values().first { it.name.equals(value, true) }
        } catch (e: Exception) {
            Reminder.TypeReminder.PERSONAL
        }
    }
}