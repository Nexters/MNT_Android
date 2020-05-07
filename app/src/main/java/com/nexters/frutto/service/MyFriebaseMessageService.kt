package com.nexters.frutto.service

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.nexters.frutto.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    //FCM을 위해 사용하는 코드

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("testtset", remoteMessage.messageType)
        if (remoteMessage.getNotification() != null) {
            val body = remoteMessage.getNotification()!!.getBody()

            val notificationBuilder = NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.app_icon) // 알림 영역에 노출 될 아이콘.
                .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                .setContentText(body) // Firebase Console 에서 사용자가 전달한 메시지내용
                .setNumber(0)
                .setDefaults(Notification.DEFAULT_VIBRATE)

            val no : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            no.notify(0,notificationBuilder.build())






        }
    }

//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    companion object {
        private val TAG = "FCM"
    }
}