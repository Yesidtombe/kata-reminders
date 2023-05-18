package com.dojo.globant.reminders.feature.reminder.add.di

import com.dojo.globant.reminders.feature.reminder.add.data.db.dao.ReminderDao
import com.dojo.globant.reminders.feature.reminder.add.data.repositories.AddReminderRepository
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.*
import com.dojo.globant.reminders.feature.reminder.list.data.repositories.GetReminderRepository
import com.dojo.globant.reminders.feature.reminder.list.domain.usecases.GetAllReminderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object AddReminderModule {

    @Provides
    fun provideGetAllReminderUseCase(repository: GetReminderRepository) =
        GetAllReminderUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideGetReminderRepository(
        reminderDao: ReminderDao
    ): GetReminderRepository = GetReminderRepository(reminderDao)

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