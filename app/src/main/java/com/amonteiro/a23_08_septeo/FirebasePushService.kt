package com.amonteiro.a23_08_septeo

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        //On récupère les informations
        val score: String = message.data["score"] ?: ""
        val time: String = message.data["time"] ?: ""

        val texte = "Le score est de $score et le temps de $time."
        println(texte)

        //On peut choisir de l'afficher en notification ou non
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationUtils.createInstantNotification(this, texte)
        }
    }

}