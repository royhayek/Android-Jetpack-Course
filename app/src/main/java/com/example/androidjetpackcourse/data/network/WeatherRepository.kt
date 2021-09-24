package com.example.androidjetpackcourse.data.network

class WeatherRepository(private val service: WeatherApi) {

    suspend fun getWeatherLocations(q: String) = service.getWeatherLocations(q)
}
