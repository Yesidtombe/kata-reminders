package com.dojo.globant.reminders.feature.reminder.detail.domain.usecases

import com.dojo.globant.reminders.feature.reminder.list.data.repositories.GetReminderRepository
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetReminderUseCase @Inject constructor(
    private val repository: GetReminderRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int): Flow<Reminder> =
        flow {
            emit(repository.getReminderById(id))
        }.flowOn(dispatcher)
}