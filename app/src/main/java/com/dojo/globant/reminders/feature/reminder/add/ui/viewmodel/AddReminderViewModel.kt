package com.dojo.globant.reminders.feature.reminder.add.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.ValidateDescriptionUseCase
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.ValidateTitleUseCase
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.ValidateTypeUseCase
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderFormEvent
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val validateTitleUseCase: ValidateTitleUseCase,
    private val validateDescriptionUseCase: ValidateDescriptionUseCase,
    private val validateTypeUseCase: ValidateTypeUseCase
) : ViewModel() {

    var state by mutableStateOf(AddReminderState())

    fun onEvent(
        event: AddReminderFormEvent,
        navController: NavController? = null
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
                navController?.let {
                    submitData(it)
                }
            }
        }
    }

    private fun submitData(navController: NavController) {
        val titleResult = validateTitleUseCase(state.title)
        val descriptionResult = validateDescriptionUseCase(state.description)
        val typeResult = validateTypeUseCase(state.type)
        val hasError = listOf(
            titleResult,
            descriptionResult,
            typeResult
        ).any { !it.successful }

        state = state.copy(
            titleError = titleResult.errorMessage,
            descriptionError = descriptionResult.errorMessage,
            typeError = typeResult.errorMessage
        )

        if (hasError)
            return

        navController.popBackStack()
    }
}