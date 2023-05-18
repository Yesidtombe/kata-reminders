package com.dojo.globant.reminders.feature.reminder.add.domain.usecases

import com.dojo.globant.reminders.feature.reminder.add.data.repositories.AddReminderRepository
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddReminderUseCase @Inject constructor(
    private val repository: AddReminderRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(data: Reminder): Flow<Boolean> =
        flow {
            emit(repository.addReminder(data))
        }.flowOn(dispatcher)
}