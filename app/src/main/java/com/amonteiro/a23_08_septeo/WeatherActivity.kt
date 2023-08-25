package com.amonteiro.a23_08_septeo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a23_08_septeo.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewModel = model
        binding.lifecycleOwner = this

        observe()

        binding.btLoad.setOnClickListener {
            model.loadData()
        }
    }

    fun observe() {
        model.data.observe(this) {

            binding.tv.text = "${it ?: "-"}"
        }

//        model.errorMessage.observe(this) {
//            binding.tvError.text = it
//            binding.tvError.isVisible = it.isNotBlank()
//        }

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }
    }

}

class WeatherViewModel : ViewModel() {
    val data = MutableLiveData<WeatherBean?>(null)
    val errorMessage = MutableLiveData("")
    val runInProgress = MutableLiveData(false)

    fun loadData() {
        //Reset
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)

        thread {
            try {
                data.postValue(WeatherAPI.getData())
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }

    }

}
