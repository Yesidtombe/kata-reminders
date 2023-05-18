package com.dojo.globant.reminders.feature.reminder.detail.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.reminders.feature.reminder.detail.domain.usecases.GetReminderUseCase
import com.dojo.globant.reminders.feature.reminder.list.ui.ReminderState
import com.dojo.globant.reminders.feature.reminder.list.ui.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailReminderViewModel @Inject constructor(
    private val getReminderUseCase: GetReminderUseCase
) : ViewModel() {

    val state = mutableStateOf(ReminderState())

    fun getReminder(id: String) {
        viewModelScope.launch {
            getReminderUseCase.invoke(id.toInt()).collect {
                state.value = it.toModel()
            }
        }
    }
}