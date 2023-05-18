package com.dojo.globant.reminders.feature.reminder.add.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

@Entity(tableName = "reminder")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "type")
    val type: Reminder.TypeReminder,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "time")
    val time: Long,
)

fun Reminder.toDatabase() = ReminderEntity(
    id = id,
    title = title,
    description = description,
    type = type,
    date = date,
    time = time
)
