package com.amonteiro.a23_08_septeo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.amonteiro.a23_08_septeo.databinding.ActivityExoNotificationBinding

class ExoNotificationActivity : AppCompatActivity() {

    val binding by lazy { ActivityExoNotificationBinding.inflate(layoutInflater) }
    //val model by lazy { ViewModelProvider(this)[ExoViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        LocationUtils.getLastKnownLocation(this)?.let {
            binding.textView.text = "${it?.latitude} ${it.longitude}"
        }

        binding.btNow.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                NotificationUtils.createInstantNotification(this, "Instant")
            }
            else {
                //Demande
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
            }
        }

        binding.btSchedule.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                NotificationUtils.scheduleNotification(this, "schedule", 5000 )
            }
            else {
                //Demande
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
            }
        }
    }



}