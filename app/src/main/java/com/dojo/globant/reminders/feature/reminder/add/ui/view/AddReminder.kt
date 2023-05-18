package com.dojo.globant.reminders.feature.reminder.add.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.composables.textfield.GenericTextField
import com.dojo.globant.reminders.common.util.toTime
import com.dojo.globant.reminders.common.util.toTimeInHours
import com.dojo.globant.reminders.common.util.toTimeInMin
import com.dojo.globant.reminders.feature.reminder.add.ui.AddReminderFormEvent
import com.dojo.globant.reminders.feature.reminder.add.ui.viewmodel.AddReminderViewModel
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminder(
    viewModel: AddReminderViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    val calendarState = rememberSheetState()
    val clockState = rememberSheetState()
    val buttonDate = remember { mutableStateOf("Date") }
    val buttonTime = remember { mutableStateOf("Time") }

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            buttonDate.value = date.toString()
            viewModel.onEvent(AddReminderFormEvent.DateChanged(date.toTime()))
        }
    )

    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            buttonTime.value = "$hours:$minutes"
            viewModel.onEvent(AddReminderFormEvent.TimeChanged(hours.toTimeInHours() + minutes.toTimeInMin()))
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        GenericTextField(
            text = state.title,
            onValueChange = {
                viewModel.onEvent(AddReminderFormEvent.TitleChanged(it))
            },
            errorMessage = state.titleError,
            label = R.string.reminder_title
        )
        GenericTextField(
            text = state.description,
            onValueChange = {
                viewModel.onEvent(AddReminderFormEvent.DescriptionChanged(it))
            },
            errorMessage = state.descriptionError,
            label = R.string.reminder_description
        )
        DropDownItem(
            viewModel = viewModel,
            name = stringResource(R.string.reminder_type),
            selectedText = state.type,
            items = listOf(Reminder.TypeReminder.WORKING.name, Reminder.TypeReminder.PERSONAL.name)
        )
        state.typeError?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                text = it.asString(),
                color = Color.Red
            )
        }
        OutlinedButton(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            border = BorderStroke(1.dp, Color.DarkGray),
            onClick = {
                calendarState.show()
            }
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = buttonDate.value,
                fontSize = 15.sp
            )
        }
        state.dateError?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                text = it.asString(),
                color = Color.Red
            )
        }
        OutlinedButton(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            border = BorderStroke(1.dp, Color.DarkGray),
            onClick = {
                clockState.show()
            }
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = buttonTime.value,
                fontSize = 15.sp
            )
        }
        state.timeError?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                text = it.asString(),
                color = Color.Red
            )
        }
        Button(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                viewModel.onEvent(
                    event = AddReminderFormEvent.OnCreateReminder,
                    navController = navController
                )
            }
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.add_new_reminder),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DropDownItem(
    viewModel: AddReminderViewModel,
    name: String,
    selectedText: String,
    items: List<String>
) {
    val expanded = remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .clickable {
                    expanded.value = true
                }
                .fillMaxWidth(),
            label = { Text(text = name) },
            value = selectedText,
            onValueChange = { },
            enabled = false,
            readOnly = true,
            shape = ShapeDefaults.Large,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            )
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        viewModel.onEvent(AddReminderFormEvent.TypeChanged(it))
                        expanded.value = false
                    }
                )
            }
        }
    }
}