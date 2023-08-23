package com.amonteiro.a23_08_septeo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a23_08_septeo.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            model.loadData()
            //Les données ont changées j'actualise
            refreshUI()
        }

        //Actualisation des données à l'arrivée sur l'écran
        refreshUI()
    }

    fun refreshUI(){
        binding.tv.text = model.data?.toString() ?: "-"

        binding.tvError.text = model.errorMessage
        binding.tvError.isVisible = model.errorMessage.isNotBlank()
    }
}

class WeatherViewModel : ViewModel() {
    var data:WeatherBean ? = null
    var errorMessage = ""

    fun loadData(){
        //Reset
        data= null
        errorMessage = ""
        try {
            data = WeatherAPI.getData()
        }
        catch (e: Exception) {
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }

}
