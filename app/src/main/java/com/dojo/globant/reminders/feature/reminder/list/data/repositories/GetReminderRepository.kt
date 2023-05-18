package com.dojo.globant.reminders.feature.reminder.list.data.repositories

import com.dojo.globant.reminders.feature.reminder.add.data.db.dao.ReminderDao
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import com.dojo.globant.reminders.feature.reminder.list.domain.model.toDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao
) {
    suspend fun getAllReminders(): List<Reminder> {
        return reminderDao.getAllReminders().map { it.toDomain() }
    }
}