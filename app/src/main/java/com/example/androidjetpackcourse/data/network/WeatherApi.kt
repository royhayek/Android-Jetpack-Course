package com.example.androidjetpackcourse.data.network

import com.example.androidjetpackcourse.data.model.weather.CurrentWeather
import com.example.androidjetpackcourse.data.model.weather.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/search.json")
    suspend fun getWeatherLocations(
        @Query("q") q: String,
    ): List<Location>

    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
    ): CurrentWeather
}
