package com.dojo.globant.reminders

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.dojo.globant.reminders.feature.reminder.list.domain.model.Reminder

class AlarmNotification : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 1
    }
    override fun onReceive(context: Context, p1: Intent?) {
        createSimpleNotification(context, p1)
    }
    private fun createSimpleNotification(context: Context, p1: Intent?) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val myReminder = p1?.let {
            it.getSerializableExtra("myReminder") as Reminder
        }
        val flag = PendingIntent.FLAG_IMMUTABLE
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context, MainActivity.MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(myReminder?.title.orEmpty())
            .setContentText(myReminder?.type?.name)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Description: ${myReminder?.description.orEmpty()}\nType: ${myReminder?.type?.name}")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}