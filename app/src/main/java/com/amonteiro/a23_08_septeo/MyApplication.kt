package com.amonteiro.a23_08_septeo

import android.app.Application
import androidx.core.app.ActivityCompat

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseUtils.printToken()

    }
}