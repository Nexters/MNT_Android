package com.example.mnt_android.service

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.mnt_android.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    //FCM을 위해 사용하는 코드

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.getNotification() != null) {
            val body = remoteMessage.getNotification()!!.getBody()

            val notificationBuilder = NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                .setContentText(body) // Firebase Console 에서 사용자가 전달한 메시지내용
                .setNumber(0)
                .setDefaults(Notification.DEFAULT_VIBRATE)

            val no : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            no.notify(0,notificationBuilder.build())






        }
    }




    override fun onNewToken(s: String) {
        super.onNewToken(s)



    }

    companion object {

        private val TAG = "asdasdasd"
    }


}