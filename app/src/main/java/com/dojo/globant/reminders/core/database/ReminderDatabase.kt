package com.dojo.globant.reminders.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dojo.globant.reminders.feature.reminder.add.data.db.dao.ReminderDao
import com.dojo.globant.reminders.feature.reminder.add.data.db.entities.ReminderEntity

@Database(entities = [ReminderEntity::class], version = 1)
abstract class ReminderDatabase: RoomDatabase() {

    abstract fun getReminderDao(): ReminderDao
}