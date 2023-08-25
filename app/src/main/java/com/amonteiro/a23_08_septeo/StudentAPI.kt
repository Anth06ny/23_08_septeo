package com.amonteiro.a23_08_septeo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.random.Random


data class WeatherBean(var name: String, var note: Int)

object WeatherAPI {

    var count = 0

    fun getData(): WeatherBean {
        Thread.sleep(1000)
        count++
        if(count%2 == 0)
            throw Exception("La requête a échoué")
        return WeatherBean("Toto", count) //Random.nextInt(20))
    }
}

fun main() {
    val student = WeatherAPI.getData()
}


private const val URL_API_WEATHER =
    "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="

//Hors cours

object RequestUtils {

    private val client = OkHttpClient()
    private val gson = Gson()

    fun loadWeather(cityName:String): WeatherBean {
        //Réaliser la requête.
        val json = sendGet(URL_API_WEATHER + cityName)

        //Parser le JSON avec le bon bean et GSON
        val data = gson.fromJson(json, WeatherBean::class.java)

        //Retourner la donnée
        return data
    }

    //Version short
    //fun loadWeather() = gson.fromJson(sendGet(URL_API_WEATHER + cityName), WeatherBean::class.java)

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}


