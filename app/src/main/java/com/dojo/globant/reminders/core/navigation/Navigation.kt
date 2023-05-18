package com.dojo.globant.reminders.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dojo.globant.reminders.feature.reminder.add.ui.view.AddReminder
import com.dojo.globant.reminders.feature.reminder.detail.ui.view.DetailReminderScreen
import com.dojo.globant.reminders.feature.reminder.list.ui.view.ReminderScreen

object Destination {
    const val ROOT = "root"
    const val ADD = "add"
    const val DETAILS = "details"
}

@Composable
fun Navigation (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destination.ROOT
    ){
        composable(route = Destination.ROOT) {
            ReminderScreen(navController = navController)
        }
        composable(route = Destination.ADD) {
            AddReminder(navController = navController)
        }
        composable(route = "${Destination.DETAILS}/{id}") {
            DetailReminderScreen(idReminder = it.arguments?.getString("id"))
        }
    }
}