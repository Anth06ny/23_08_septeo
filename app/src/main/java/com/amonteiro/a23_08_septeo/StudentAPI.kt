package com.amonteiro.a23_08_septeo

import kotlin.random.Random


data class WeatherBean(var name: String, var note: Int)

object WeatherAPI {

    var count = 0

    fun getData(): WeatherBean {
        Thread.sleep(2000)
        count++
        if(count%2 == 0)
            throw Exception("La requête a échoué")
        return WeatherBean("Toto", count) //Random.nextInt(20))
    }
}

fun main() {
    val student = WeatherAPI.getData()
}



