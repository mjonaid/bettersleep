package com.task.bettersleep.presentation.sleep

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.task.bettersleep.NotificationUtils

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val notificationUtils = NotificationUtils(context)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(150, notification)
    }
}