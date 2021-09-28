package com.example.androidjetpackcourse.data.network

import com.example.androidjetpackcourse.BuildConfig
import com.example.androidjetpackcourse.data.model.weather.Location
import com.example.androidjetpackcourse.di.BaseUrlInterceptor

class WeatherRepository(
    private val service: WeatherApi,
    private val interceptor: BaseUrlInterceptor
) {
    
    suspend fun getWeatherLocations(q: String): List<Location> {
        interceptor.setHost(BuildConfig.WEATHER_API_URL)
        return service.getWeatherLocations(q)
    }
}
