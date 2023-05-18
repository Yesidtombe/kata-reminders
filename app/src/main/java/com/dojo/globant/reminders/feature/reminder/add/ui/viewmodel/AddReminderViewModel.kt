package com.dojo.globant.reminders.feature.reminder.add.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.feature.reminder.add.domain.usecases.*
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderFormEvent
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderState
import com.dojo.globant.reminders.feature.reminder.list.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val validateTitleUseCase: ValidateTitleUseCase,
    private val validateDescriptionUseCase: ValidateDescriptionUseCase,
    private val validateTypeUseCase: ValidateTypeUseCase,
    private val validateDateUseCase: ValidateDateUseCase,
    private val validateTimeUseCase: ValidateTimeUseCase,
    private val addReminderUseCase: AddReminderUseCase
) : ViewModel() {

    var state by mutableStateOf(AddReminderState())

    fun onEvent(
        event: AddReminderFormEvent,
        navController: NavController? = null,
        context: Context? = null
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
            is AddReminderFormEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }
            is AddReminderFormEvent.TimeChanged -> {
                state = state.copy(time = event.time)
            }
            is AddReminderFormEvent.OnCreateReminder -> {
                navController?.let {
                    submitData(it, context)
                }
            }
        }
    }

    private fun submitData(navController: NavController, context: Context?) {
        val titleResult = validateTitleUseCase(state.title)
        val descriptionResult = validateDescriptionUseCase(state.description)
        val typeResult = validateTypeUseCase(state.type?.name.orEmpty())
        val dateResult = validateDateUseCase(state.date)
        val timeResult = validateTimeUseCase(state.time)
        val hasError = listOf(
            titleResult,
            descriptionResult,
            typeResult,
            dateResult,
            timeResult
        ).any { !it.successful }

        state = state.copy(
            titleError = titleResult.errorMessage,
            descriptionError = descriptionResult.errorMessage,
            typeError = typeResult.errorMessage,
            dateError = dateResult.errorMessage,
            timeError = timeResult.errorMessage
        )

        if (hasError)
            return

        addReminder(navController, context)
    }

    private fun addReminder(navController: NavController, context: Context?) {
        viewModelScope.launch {
            addReminderUseCase(state.toDomain()).collect { result ->
                if (result) {
                    context?.let { ctx ->
                        Toast.makeText(ctx, ctx.getString(R.string.reminder_created_message), Toast.LENGTH_SHORT).show()
                    }
                    navController.popBackStack()
                }
            }
        }
    }
}