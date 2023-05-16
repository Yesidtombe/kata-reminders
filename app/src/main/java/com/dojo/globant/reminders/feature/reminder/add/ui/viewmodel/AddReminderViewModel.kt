package com.dojo.globant.reminders.feature.reminder.add.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.ValidateTitleUseCase
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderFormEvent
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val validateTitleUseCase: ValidateTitleUseCase
) : ViewModel() {

    var state by mutableStateOf(AddReminderState())

    fun onEvent(
        event: AddReminderFormEvent
    ) {
        when (event) {
            is AddReminderFormEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }
            is AddReminderFormEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is AddReminderFormEvent.TypeChanged -> {
                state = state.copy(type = event.type)
            }
            is AddReminderFormEvent.OnCreateReminder -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val titleResult = validateTitleUseCase(state.title)

    }
}