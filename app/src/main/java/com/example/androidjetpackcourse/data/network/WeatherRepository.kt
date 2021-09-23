package com.example.androidjetpackcourse.data.network

class WeatherRepository(private val service: WeatherApi) {

    suspend fun getWeatherLocations(key: String, q: String) = service.getWeatherLocations(key, q)
}
