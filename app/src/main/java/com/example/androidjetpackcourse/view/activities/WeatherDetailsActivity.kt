package com.example.androidjetpackcourse.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.androidjetpackcourse.data.model.weather.CurrentWeather
import com.example.androidjetpackcourse.databinding.ActivityWeatherDetailsBinding
import com.example.androidjetpackcourse.handlers.Status
import com.example.androidjetpackcourse.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailsBinding
    private val weatherViewModel by viewModel<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrentWeather()
        observeData()
    }

    private fun getCurrentWeather() {
        val region : String = intent.getStringExtra("region").toString()
        weatherViewModel.getCurrentWeather(region)
    }

    private fun observeData() {
        weatherViewModel.currentWeather.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        cl_weather.visibility = View.VISIBLE
                        pb_weather_loading.visibility = View.GONE
                        resource.data?.let { weather -> bindData(weather) }
                    }
                    Status.ERROR -> {
                        cl_weather.visibility = View.VISIBLE
                        pb_weather_loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        pb_weather_loading.visibility = View.VISIBLE
                        cl_weather.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun bindData(weather: CurrentWeather) {
        tv_location_name.text = weather.location.name
        tv_last_updated.text = "Updated at: " + weather.current.last_updated.replace("-", "/")
        tv_condition_text.text = weather.current.condition.text
        tv_weather_temp.text = weather.current.temp_c.toInt().toString() + "°C"
        tv_pressure.text = weather.current.pressure_mb.toString()
        tv_humidity.text = weather.current.humidity.toString()
        tv_wind_speed.text = weather.current.wind_kph.toString()
        tv_about.text = "WeatherApi"
    }
}
