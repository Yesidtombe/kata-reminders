package com.dojo.globant.reminders.feature.reminder.list.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.reminders.core.navigation.Destination
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import com.dojo.globant.reminders.feature.reminder.list.ui.ReminderState
import com.dojo.globant.reminders.feature.reminder.list.ui.viewmodel.ListReminderViewModel

@Composable
fun ReminderScreen(
    viewModel: ListReminderViewModel = hiltViewModel(),
    navController: NavController
) {
    val items = viewModel.items

    Scaffold(
        floatingActionButton = {
            FAB {
                navController.navigate(route = Destination.ADD)
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            items.value.forEach {
                MyReminder(item = it)
            }
        }
    }
}

@Composable
fun MyReminder(
    item: ReminderState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = item.image),
                contentDescription = item.title
            )
        }
        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Column {
                Text(text = item.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Column {
                Text(text = item.date)
            }
            Column {
                Text(text = item.type?.name ?: Reminder.TypeReminder.PERSONAL.name, fontSize = 12.sp)
            }
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .height(1.dp)
            .background(Color.LightGray)
    )
}

@Composable
fun FAB(onAddClicked: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = onAddClicked
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}