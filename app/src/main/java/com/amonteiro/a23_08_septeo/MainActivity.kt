package com.amonteiro.a23_08_septeo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amonteiro.a23_08_septeo.databinding.ActivityMainBinding
import java.io.Serializable
import kotlin.concurrent.fixedRateTimer



class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("MainActivity.onCreate")
        setContentView(binding.root)

        binding.btValidate.setOnClickListener {

            //Envoie
            //val intent = Intent(this, WeatherActivity::class.java)
            val intent = Intent(this, ExoNavGraphActivity::class.java)

            startActivity(intent)

//            if (binding.rbLike.isChecked) {
//                binding.et.setText(binding.rbLike.text)
//            }
//            if (binding.rbDislike.isChecked) {
//                binding.et.setText(binding.rbDislike.text)
//            }
            binding.iv.setImageResource(R.drawable.baseline_flag_24)
        }

//        binding.btCancel.setOnClickListener {
//            binding.rg.clearCheck()
//            binding.et.setText("")
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,1,0,"Notificaiton")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == 1) {
            //reidrection uniquement si on a la permission
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this, ExoNotificationActivity::class.java))
            }
            else {
                //Demande
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Callback de la demande de permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //redirection uniquement si on a la permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(this, ExoNotificationActivity::class.java))
        }
        else {
            //Demande
            Toast.makeText(this,"Il faut la permission", Toast.LENGTH_LONG).show()
        }
    }


    override fun onStart() {
        super.onStart()
        binding.et.setText(getString(R.string.l_cran_est_visible, "10h"))
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity.onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity.onDestroy")

    }


}