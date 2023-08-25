package com.amonteiro.a23_08_septeo

import android.Manifest
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        println("onReceive")
        val notification = intent.getParcelableExtra<Notification>("Macle") ?: return

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            //Envoyer la notification
            println("send")
            val ncm = NotificationManagerCompat.from(context)
            ncm.notify(29, notification)
        }

    }
}