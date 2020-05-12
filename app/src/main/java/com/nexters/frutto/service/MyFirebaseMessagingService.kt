package com.nexters.frutto.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nexters.frutto.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nexters.frutto.service.repository.PreferencesRepository


class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private const val CHANNEL_ID = 0
        private const val CHANNEL_NAME = "Notification"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if(PreferencesRepository(applicationContext).getOnNotification()) {
            createNotificationChannel()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                remoteMessage.notification?.run {

                    val notificationBuilder = Notification.Builder(applicationContext, CHANNEL_NAME)
                        .setSmallIcon(R.mipmap.app_icon)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(body)

                    with(NotificationManagerCompat.from(applicationContext)) {
                        notify(CHANNEL_ID, notificationBuilder.build())
                    }
                }
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_NAME, CHANNEL_NAME, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}