package com.example.androidjetpackcourse.data.model.weather

data class CurrentWeather(
    val current: Current,
    val location: Location
)

data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Int,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Int,
    val temp_c: Int,
    val temp_f: Double,
    val uv: Int,
    val vis_km: Int,
    val vis_miles: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
)

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)
