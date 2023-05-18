package com.dojo.globant.reminders.feature.reminder.list.domain.usecases

import com.dojo.globant.reminders.feature.reminder.list.data.repositories.GetReminderRepository
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllReminderUseCase @Inject constructor(
    private val repository: GetReminderRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<Reminder>> =
        flow {
            emit(repository.getAllReminders())
        }.flowOn(dispatcher)
}