package com.dojo.globant.reminders.feature.reminder.list.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dojo.globant.reminders.core.navigation.Destination
import com.dojo.globant.reminders.feature.reminder.list.domain.usecases.GetAllReminderUseCase
import com.dojo.globant.reminders.feature.reminder.list.ui.ReminderState
import com.dojo.globant.reminders.feature.reminder.list.ui.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListReminderViewModel @Inject constructor(
    private val getAllReminderUseCase: GetAllReminderUseCase
) : ViewModel() {

    private var _items = mutableStateOf<List<ReminderState>>(listOf())
    val items get() = _items

    init {
        viewModelScope.launch {
            getAllReminderUseCase().collect { reminders ->
                _items.value = reminders.map { it.toModel() }
            }
        }
    }

    fun navigateToDetail(navController: NavController, idReminder: Int) {
        navController.navigate("${Destination.DETAILS}/$idReminder")
    }
}