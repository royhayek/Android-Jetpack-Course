package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.*
import com.example.androidjetpackcourse.data.model.weather.CurrentWeather
import com.example.androidjetpackcourse.data.model.weather.Location
import com.example.androidjetpackcourse.data.network.WeatherRepository
import com.example.androidjetpackcourse.handlers.Resource
import kotlinx.coroutines.*

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _locationsList: MutableLiveData<Resource<List<Location>>> = MutableLiveData()
    val locationsList: LiveData<Resource<List<Location>>>
        get() = _locationsList

    private val _currentWeather: MutableLiveData<Resource<CurrentWeather>> = MutableLiveData()
    val currentWeather: LiveData<Resource<CurrentWeather>>
        get() = _currentWeather

    fun getWeatherLocations(q: String) {
        viewModelScope.launch {
            _locationsList.postValue(Resource.loading(null))
            try {
                val response = repository.getWeatherLocations(q)
                _locationsList.postValue(Resource.success(response))
            } catch (e: Exception) {
                _locationsList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getCurrentWeather(q: String) {
        viewModelScope.launch {
            _currentWeather.postValue(Resource.loading(null))
            try {
                val response = repository.getCurrentWeather(q)
                _currentWeather.postValue(Resource.success(response))
            } catch (e: Exception) {
                _currentWeather.postValue(Resource.error(e.toString(), null))
            }
        }
    }
}
