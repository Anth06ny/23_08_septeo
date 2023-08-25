package com.amonteiro.a23_08_septeo

import android.Manifest
import android.app.AlarmManager
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH

object NotificationUtils {

    const val CHANNEL_ID = "MonSuperChannel"
    const val CHANNEL_NAME = "Commandes"

    //Création du channel
    private fun initChannel(c: Context) {
        val nm = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Réglage du channel
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.description = "Commandes" // Description
        channel.enableLights(true) // Lumière
        channel.enableVibration(true) // Vibration
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        // Création du channel
        nm.createNotificationChannel(channel)
    }

    //Je reporte le controle de permission au dessus
    //@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
//Envoyer une notification immediate
    fun createInstantNotification(c: Context, message: String) {

        //Initialisation du chanel
        initChannel(c)
        //Ce qui se passera quand on cliquera sur la notif
        val intent = Intent(c, MainActivity::class.java)
        var flags = PendingIntent.FLAG_ONE_SHOT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            //Notification modifiable
            flags = flags or PendingIntent.FLAG_MUTABLE
        }
        val pi = PendingIntent.getActivity(c, 0, intent, flags)

        //La notification
        val nb = NotificationCompat.Builder(c, CHANNEL_ID)
        nb.setSmallIcon(R.drawable.baseline_delete_24).setContentTitle("Le Titre")
            .setContentText(message)
            .setContentIntent(pi) //le clic dessus
            .setPriority(IMPORTANCE_HIGH)
            .setDefaults(DEFAULT_ALL) //Son + afficher la notification

        //Envoyer la notification
        val ncm = NotificationManagerCompat.from(c)
        ncm.notify(29, nb.build())
    }

    fun scheduleNotification(c: Context, message: String, delay: Long) {
        //Initialisation du chanel
        initChannel(c)

        //La notification
        val builder = NotificationCompat.Builder(c, CHANNEL_ID)
        builder.setContentTitle("Scheduled Notification")
        builder.setContentText(message)
        builder.setSmallIcon(R.drawable.baseline_flag_24)

        //Redirection vers le broadcast

        val intent = Intent(c, NotificationReceiver::class.java)
        intent.putExtra("MaCle", builder.build())
        var flags = PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            //Notification modifiable
            flags = flags or PendingIntent.FLAG_MUTABLE ;
        }
        val pendingIntent = PendingIntent.getBroadcast(c, 0, intent, flags)

        //Temps dans le futur
        println("AlarmeManager")
        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = c.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }
}