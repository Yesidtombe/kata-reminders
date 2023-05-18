package com.dojo.globant.reminders.feature.reminder.add.di

import com.dojo.globant.reminders.feature.reminder.add.data.db.dao.ReminderDao
import com.dojo.globant.reminders.feature.reminder.add.data.repositories.AddReminderRepository
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object AddReminderModule {

    @Provides
    fun provideAddReminderRepository(
        reminderDao: ReminderDao
    ): AddReminderRepository = AddReminderRepository(reminderDao)

    @Provides
    fun provideAddReminderUseCase(repository: AddReminderRepository) =
        AddReminderUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideValidateTitleUseCase() = ValidateTitleUseCase()

    @Provides
    fun provideValidateDescriptionUseCase() = ValidateDescriptionUseCase()

    @Provides
    fun provideValidateTypeUseCase() = ValidateTypeUseCase()

    @Provides
    fun provideValidateDateUseCase() = ValidateDateUseCase()

    @Provides
    fun provideValidateTimeUseCase() = ValidateTimeUseCase()

}