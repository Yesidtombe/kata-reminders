package com.dojo.globant.reminders.feature.reminder.add.data.repositories

import com.dojo.globant.reminders.feature.reminder.add.data.db.dao.ReminderDao
import com.dojo.globant.reminders.feature.reminder.add.data.db.entities.toDatabase
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AddReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao
) {
    suspend fun addReminder(data: Reminder): Boolean {
        return reminderDao.insertReminder(data.toDatabase()) != 0L
    }
}