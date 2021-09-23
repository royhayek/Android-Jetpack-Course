package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.androidjetpackcourse.data.model.weather.Location
import com.example.androidjetpackcourse.data.network.WeatherRepository
import com.example.androidjetpackcourse.handlers.Resource
import kotlinx.coroutines.*

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel(){

    fun getWeatherLocations(key: String, q: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getWeatherLocations(key, q)))
        } catch (exception: Exception) {
            emit(Resource.error(msg = exception.message ?: "Error Occurred!", data = null))
        }
    }
}
