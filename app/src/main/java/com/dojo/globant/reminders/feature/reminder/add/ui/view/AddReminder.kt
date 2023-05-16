package com.dojo.globant.reminders.feature.reminder.add.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.common.composables.textfield.GenericTextField
import com.dojo.globant.reminders.feature.reminder.add.ui.viewmodel.AddReminderViewModel
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

@Composable
fun AddReminder(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        GenericTextField(
            text = stringResource(R.string.reminder_title), onValueChange = {}
        )
        GenericTextField(
            text = stringResource(R.string.reminder_description), onValueChange = {}
        )
        val typeReminder = remember { mutableStateOf("") }
        DropDownItem(
            name = stringResource(R.string.reminder_type),
            selectedText = typeReminder,
            items = listOf(Reminder.TypeReminder.WORKING.name, Reminder.TypeReminder.PERSONAL.name)
        )
    }
}

@Composable
fun DropDownItem(
    name: String,
    selectedText: MutableState<String>,
    items: List<String>
) {
    val expanded = remember { mutableStateOf(false) }
    Column(Modifier.padding(8.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .clickable {
                    expanded.value = true
                }
                .fillMaxWidth(),
            label = { Text(text = name) },
            value = selectedText.value,
            onValueChange = {
                selectedText.value = it
            },
            enabled = false,
            readOnly = true,
            shape = ShapeDefaults.Large,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.DarkGray,
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
                        selectedText.value = it
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview() {
    AddReminder()
}