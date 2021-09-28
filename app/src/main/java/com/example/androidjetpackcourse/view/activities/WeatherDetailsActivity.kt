package com.example.androidjetpackcourse.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.adapters.WeatherDetailsAdapter
import com.example.androidjetpackcourse.data.model.weather.CurrentWeather
import com.example.androidjetpackcourse.data.model.weather.WeatherDetail
import com.example.androidjetpackcourse.databinding.ActivityWeatherDetailsBinding
import com.example.androidjetpackcourse.handlers.Status
import com.example.androidjetpackcourse.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class WeatherDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailsBinding
    private lateinit var adapter: WeatherDetailsAdapter
    private val weatherViewModel by viewModel<WeatherViewModel>()
    private val detailsList = ArrayList<WeatherDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGridView()
        getCurrentWeather()
        observeData()
    }

    private fun initGridView() {
        adapter = WeatherDetailsAdapter(detailsList)
        gv_details.adapter = adapter
        gv_details.numColumns = 3
        gv_details.horizontalSpacing = 30
        gv_details.verticalSpacing = 30
        gv_details.stretchMode = GridView.STRETCH_COLUMN_WIDTH
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

        detailsList.add(WeatherDetail(icon = R.drawable.ic_sunrise, title = "Sunrise", value = "06:40 AM"))
        detailsList.add(WeatherDetail(icon = R.drawable.ic_sunset, title = "Sunset", value = "06:40 PM"))
        detailsList.add(WeatherDetail(icon = R.drawable.ic_wind, title = "Wind", value = weather.current.wind_kph.toString()))
        detailsList.add(WeatherDetail(icon = R.drawable.ic_pressure, title = "Pressure", value = weather.current.pressure_mb.toString()))
        detailsList.add(WeatherDetail(icon = R.drawable.ic_humidity, title = "Humidity", value = weather.current.humidity.toString()))
        detailsList.add(WeatherDetail(icon = R.drawable.ic_about, title = "Created By", value = "WeatherApi"))

        adapter.notifyDataSetChanged()
    }
}
