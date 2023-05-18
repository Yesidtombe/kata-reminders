package com.dojo.globant.reminders.feature.reminder.add.di

import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object AddReminderModule {

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