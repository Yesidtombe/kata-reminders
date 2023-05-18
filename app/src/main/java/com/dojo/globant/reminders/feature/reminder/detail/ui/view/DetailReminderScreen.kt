package com.dojo.globant.reminders.feature.reminder.detail.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.reminders.feature.reminder.detail.ui.viewmodel.DetailReminderViewModel

@Composable
fun DetailReminderScreen(
    viewModel: DetailReminderViewModel = hiltViewModel(),
    idReminder: String?
) {
    viewModel.getReminder(idReminder.orEmpty())
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        Text(text = state.value.title)
        Text(text = state.value.description)
        Text(text = state.value.type?.name.orEmpty())
        Text(text = state.value.date)
    }
}