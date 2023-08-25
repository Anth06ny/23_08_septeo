package com.amonteiro.a23_08_septeo

import com.google.firebase.messaging.FirebaseMessaging

object FirebaseUtils {
    //Affiche le token courant dans la console
    fun printToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                println("Fetching FCM registration token failed")
                task.exception?.printStackTrace()
            }
            else {
                // Get new FCM registration token
                println("CurrentToken=${task.result}")
            }
        }
    }
}
