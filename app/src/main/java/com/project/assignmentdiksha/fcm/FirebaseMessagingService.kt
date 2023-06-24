package com.project.assignmentdiksha.fcm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.project.assignmentdiksha.ui.activity.MainActivity
import com.project.assignmentdiksha.MainApplication
import com.project.assignmentdiksha.R

import java.util.*


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(fcmToken: String) {
        super.onNewToken(fcmToken)
        Log.d("fcmToken================",fcmToken)
        if (!Objects.isNull(fcmToken) && !TextUtils.isEmpty(fcmToken)) {
            Log.d("Token",fcmToken)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title ?: "Default Title"
        val message = remoteMessage.notification?.body ?: "Default message"
        val data=remoteMessage.data.get("1")
        data?.let { sendPush(title,message, it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant", "UnspecifiedImmutableFlag")
    private fun sendPush(title: String, message: String,data:String) {
        val mChannelId = getString(R.string.default_notification_channel_id)
        val mImportance = NotificationManager.IMPORTANCE_HIGH
        val mChannelName = getString(R.string.app_name)

        val notificationIntent = Intent(this, MainActivity::class.java)
         notificationIntent.putExtra("PayLoad",data)

        val notificationId = System.currentTimeMillis().toInt()
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                MainApplication.instance,
                notificationId,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }
        else {
            PendingIntent.getActivity(
                MainApplication.instance,
                notificationId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_heart_big)

        val notificationBuilder = NotificationCompat.Builder(this, mChannelId)
            .setSmallIcon(R.drawable.ic_heart_big)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(defaultSoundUri)
            .setLargeIcon(largeIcon)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)


        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        /*
         *********************   push for android oreo version ************
         */
        val mChannel = NotificationChannel(
            mChannelId, mChannelName, mImportance
        )
        notificationManager.createNotificationChannel(mChannel)
        //handle notification manager notify may null issue
        notificationManager.notify(
            notificationId ,
            notificationBuilder.build()
        )
    }
}