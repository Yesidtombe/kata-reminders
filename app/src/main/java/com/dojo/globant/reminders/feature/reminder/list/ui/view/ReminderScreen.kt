package com.dojo.globant.reminders.feature.reminder.list.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.globant.reminders.R
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReminderScreen() {
    val items = listOf(
        Reminder(1, "Recordatorio 1", "Este es el recordatorio 1", Reminder.TypeReminder.WORKING, R.drawable.round_access_time, Date().time, Date().time),
        Reminder(2, "Recordatorio 2", "Este es el recordatorio 2", Reminder.TypeReminder.PERSONAL, R.drawable.round_access_time, Date().time, Date().time),
        Reminder(3, "Recordatorio 3", "Este es el recordatorio 3", Reminder.TypeReminder.WORKING, R.drawable.round_access_time, Date().time, Date().time)
    )

    Scaffold(
        floatingActionButton = {
            FAB {

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            items.forEach {
                MyReminder(item = it)
            }
        }
    }
}

@Composable
fun MyReminder(
    item: Reminder
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
                modifier = Modifier.width(42.dp).height(42.dp),
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
                val date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                Text(text = date.format(item.date))
            }
            Column {
                Text(text = item.type.name, fontSize = 12.sp)
            }
        }
    }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview() {
    ReminderScreen()
}