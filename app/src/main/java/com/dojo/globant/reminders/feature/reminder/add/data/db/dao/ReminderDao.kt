package com.dojo.globant.reminders.feature.reminder.add.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dojo.globant.reminders.feature.reminder.add.data.db.entities.ReminderEntity

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminder")
    suspend fun getAllReminders(): List<ReminderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity) : Long

}