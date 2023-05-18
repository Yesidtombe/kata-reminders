package com.dojo.globant.reminders.core.di

import android.content.Context
import androidx.room.Room
import com.dojo.globant.reminders.core.database.ReminderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val REMINDER_DATABASE_NAME = "reminder_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ReminderDatabase::class.java, REMINDER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideReminderDao(db: ReminderDatabase) = db.getReminderDao()
}