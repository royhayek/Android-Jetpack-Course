package com.example.androidjetpackcourse.services

import android.content.Intent
import android.util.Log
import com.example.androidjetpackcourse.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelName = "com.example.androidjetpackcourse"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.e("NEW_TOKEN", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.notification != null) {
            startService(remoteMessage)
        }
    }

    private fun startService(remoteMessage: RemoteMessage) {
        val serviceIntent = Intent(this, NotificationService::class.java)
        serviceIntent.action = Constants.STARTFOREGROUND_ACTION
        serviceIntent.putExtra("remoteMessage", remoteMessage)
        startService(serviceIntent)
    }
}

