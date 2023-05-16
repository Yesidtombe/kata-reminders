package com.dojo.globant.reminders.feature.reminder.add.di

import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.ValidateTitleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object AddReminderModule {

    @Provides
    fun provideValidateTitleUseCase() = ValidateTitleUseCase()

}