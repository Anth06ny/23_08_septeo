package com.amonteiro.a23_08_septeo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)

//            if (binding.rbLike.isChecked) {
//                binding.et.setText(binding.rbLike.text)
//            }
//            if (binding.rbDislike.isChecked) {
//                binding.et.setText(binding.rbDislike.text)
//            }
//            binding.iv.setImageResource(R.drawable.baseline_delete_24)
        }

//        binding.btCancel.setOnClickListener {
//            binding.rg.clearCheck()
//            binding.et.setText("")
//        }
    }


    override fun onStart() {
        super.onStart()
        println("MainActivity.onStart")
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